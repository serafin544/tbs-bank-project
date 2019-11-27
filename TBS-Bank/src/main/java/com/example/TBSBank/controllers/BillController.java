package com.example.TBSBank.controllers;
import com.example.TBSBank.checkers.ResponseStatus;

import com.example.TBSBank.models.Bill;
import com.example.TBSBank.services.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BillController {

    @Autowired
    private BillService billService;

    @GetMapping("/accounts/{accountId}/bills")
    public ResponseEntity<?> getAllBillsForAcc(@PathVariable Long accountId) {
        ResponseStatus response = new ResponseStatus();
        List<Bill> bills = billService.getAllBillsForAccount(accountId);
        if(bills == null) {
            response.setCode(HttpStatus.NOT_FOUND.value());
            response.setMessage("error fetching bills");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }else{
            response.setCode(HttpStatus.OK.value());
            response.setData(bills);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @GetMapping("/bills/{billId}")
    public ResponseEntity<?> getBillById(@PathVariable("billId") Long id) {
        ResponseStatus response = new ResponseStatus();
        Bill b = billService.getBillById(id);
        if(b == null) {
            response.setCode(HttpStatus.NOT_FOUND.value());
            response.setMessage("error fetching bill with id " + id);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }else{
            response.setCode(HttpStatus.OK.value());
            response.setData(b);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @GetMapping("/customers/{customerId}/bills")
    public ResponseEntity<?> getAllBillsForCus(@PathVariable Long customerId) {
        ResponseStatus response = new ResponseStatus();
        List<Bill> bills = billService.getAllBillsForCustomer(customerId);
        if(bills == null) {
            response.setCode(HttpStatus.NOT_FOUND.value());
            response.setMessage("error fetching bills");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }else{
            response.setCode(HttpStatus.OK.value());
            response.setData(bills);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @PostMapping("/accounts/{accountId}/bills")
    public ResponseEntity<?> createBill(@RequestBody Bill bill, @PathVariable Long accountId) {
        ResponseStatus response = new ResponseStatus();
        Bill b = billService.createBill(bill, accountId);
        if(b == null) {
            response.setCode(HttpStatus.NOT_FOUND.value());
            response.setMessage("Error creating bill: Account not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }else{
            response.setCode(HttpStatus.CREATED.value());
            response.setMessage("Created bill and added it to the account");
            response.setData(b);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
    }

    @PutMapping("/bills/{billId}")
    public ResponseEntity<?> updateBill(@PathVariable("billId") Long id, @RequestBody Bill bill) {
        ResponseStatus response = new ResponseStatus();
        Bill b = billService.updateBill(id, bill);
        if(b == null) {
            response.setCode(HttpStatus.NOT_FOUND.value());
            response.setMessage("Bill ID does not exist");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }else{
            billService.updateBill(id, bill);
            response.setCode(HttpStatus.ACCEPTED.value());
            response.setMessage("Accepted bill modification");
            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        }
    }

    @DeleteMapping("/bills/{billId}")
    public ResponseEntity<?> deleteBill(@PathVariable("billId") Long id) {
        ResponseStatus response = new ResponseStatus();
        if(billService.getBillById(id) == null) {
            response.setCode(HttpStatus.NOT_FOUND.value());
            response.setMessage("This id does not exist in bills");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }else{
            billService.deleteBill(id);
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        }
    }
}
