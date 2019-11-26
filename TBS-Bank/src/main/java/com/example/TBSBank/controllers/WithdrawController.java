package com.example.TBSBank.controllers;

import com.example.TBSBank.checkers.ResponseStatus;
import com.example.TBSBank.models.Bill;
import com.example.TBSBank.models.Withdraw;
import com.example.TBSBank.services.AccountService;
import com.example.TBSBank.services.WithdrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class WithdrawController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private WithdrawService withdrawService;

    @GetMapping("/accounts/{accountId}/withdraws")
    public ResponseEntity<?> getAllWithdrawsForAcc(@PathVariable Long accountId, @RequestBody Withdraw withdraw) {
        ResponseStatus response = new ResponseStatus();
        List<Withdraw> withdraws = withdrawService.getAllWithdrawsForAccount(accountId);
        if(withdraws == null) {
            response.setCode(HttpStatus.NOT_FOUND.value());
            response.setMessage("error fetching bills");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }else{
            response.setCode(HttpStatus.OK.value());
            response.setData(withdraws);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

        @GetMapping("/withdraw/{withdrawId}")
        public ResponseEntity<?> getWithdrawById(@PathVariable("withdrawId") Long id) {
            ResponseStatus response = new ResponseStatus();
            Optional<Withdraw> w = withdrawService.getWithdrawById(id);
            if(!w.isPresent()) {
                response.setCode(HttpStatus.NOT_FOUND.value());
                response.setMessage("error fetching withdraw with id " + id);
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }else{
                response.setCode(HttpStatus.OK.value());
                response.setData(w);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        }

        @PostMapping("/accounts/{accountId}/withdraws")
        public ResponseEntity<?> createWithdraw(@RequestBody Withdraw withdraw, @PathVariable Long accountId) {
            ResponseStatus response = new ResponseStatus();
            Withdraw w = withdrawService.createWithdraw(withdraw, accountId);
            if(w == null) {
                response.setCode(HttpStatus.NOT_FOUND.value());
                response.setMessage("Error creating withdraw: Account not found");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }else{
                response.setCode(HttpStatus.CREATED.value());
                response.setMessage("Created withdraw and added it to the account");
                response.setData(w);
                return new ResponseEntity<>(response, HttpStatus.CREATED);
            }
        }

        @PutMapping("/withdraw/{withdrawId}")
        public ResponseEntity<?> updateWithdraw(@PathVariable("withdrawId") Long id, @RequestBody Withdraw withdraw) {
            ResponseStatus response = new ResponseStatus();
            Withdraw w = withdrawService.updateWithdraw(id, withdraw);
            if(w == null) {
                response.setCode(HttpStatus.NOT_FOUND.value());
                response.setMessage("Bill ID does not exist");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }else{
                withdrawService.updateWithdraw(id, withdraw);
                response.setCode(HttpStatus.ACCEPTED.value());
                response.setMessage("Accepted withdraw modification");
                return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
            }
    }

        @DeleteMapping("/withdraw/{withdrawId}")
        public ResponseEntity<?> deleteWithdraw(@PathVariable("withdrawId") Long id) {
            ResponseStatus response = new ResponseStatus();
            if(!withdrawService.getWithdrawById(id).isPresent()) {
                response.setCode(HttpStatus.NOT_FOUND.value());
                response.setMessage("This id does not exist in withdraws");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }else{
                withdrawService.deleteWithdrawId(id);
                return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
            }
        }
    }

