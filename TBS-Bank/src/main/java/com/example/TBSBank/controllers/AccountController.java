package com.example.TBSBank.controllers;

import com.example.TBSBank.checkers.ResponseStatus;
import com.example.TBSBank.models.Account;
import com.example.TBSBank.services.AccountService;
import com.example.TBSBank.services.CustomerService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;


@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping( value = "/accounts")
    public ResponseEntity<?> getAllAccounts(Account accounts){
        List<Account> allAccounts = (List<Account>) accountService.getAllAccounts(accounts);
        ResponseStatus rep = new ResponseStatus();
        if(!allAccounts.isEmpty()){
            rep.setCode(HttpStatus.OK.value());
            rep.setData(allAccounts);
            return new ResponseEntity<>(rep,HttpStatus.OK);
        }else{
            rep.setCode(HttpStatus.NOT_FOUND.value());
            return new ResponseEntity<>(rep, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping( value = "/accounts/{id}")
    public ResponseEntity<?> getAccountById(@PathVariable Long id){
        ResponseStatus rep = new ResponseStatus();
        Optional<Account> account = Optional.ofNullable(accountService.getAccountById(id));
        if(account.isPresent()){
            rep.setCode(HttpStatus.OK.value());
            rep.setData(account);
            return new ResponseEntity<>(rep, HttpStatus.OK);
        }else{
            rep.setCode(HttpStatus.NOT_FOUND.value());
            return new ResponseEntity<>(rep,HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/customers/{id}/accounts")
    public ResponseEntity<?> getAccountForCustomer(@PathVariable Long id){
        Optional<Account> CustomerAccounts = Optional.ofNullable(accountService.getAccountById(id));
        ResponseStatus response = new ResponseStatus();
        if(CustomerAccounts.isPresent()){
            response.setCode(HttpStatus.OK.value());
            response.setData(CustomerAccounts);
            return new ResponseEntity<>(response,HttpStatus.OK);
        }else{
            response.setCode(HttpStatus.NOT_FOUND.value());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping( value = "/customers/{id}/accounts")
    public ResponseEntity<?> addAccount(@RequestBody Account account, @PathVariable Long id)
    {
       Account a = accountService.addAccount(id,account);
        if(a == null){
            HttpHeaders responseHeaders = new HttpHeaders();
            URI newAcctUri = ServletUriComponentsBuilder
                    .fromCurrentRequestUri()
                    .path("/{id}")
                    .buildAndExpand(a.getId())
                    .toUri();
            responseHeaders.setLocation(newAcctUri);
            return new ResponseEntity<>(null,responseHeaders,HttpStatus.CREATED);
        }
        else
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping( value = "/accounts/{id}")
    public ResponseEntity<?> updateAccount(@Valid @RequestBody Account account, @PathVariable("id") Long accountId) {
      ResponseStatus responseStatus = new ResponseStatus();
        accountService.updateAccount(accountId, account);
      return new ResponseEntity<>(HttpStatus.CREATED,HttpStatus.OK);
    }

    @DeleteMapping( value = "/accounts/{id}")
    public ResponseEntity<?> delAccount(@RequestBody Long id){
        Response response = new Response();
        accountService.deleteAccount(id);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
