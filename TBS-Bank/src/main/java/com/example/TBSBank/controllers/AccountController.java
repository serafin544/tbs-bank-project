package com.example.TBSBank.controllers;

import com.example.TBSBank.checkers.ResponseStatus;
import com.example.TBSBank.models.Account;
import com.example.TBSBank.services.AccountService;
import com.example.TBSBank.services.CustomerService;
import org.apache.coyote.Response;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class AccountController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    AccountService accountService;

    @Autowired
    CustomerService customerService;


//get all accounts
        public Iterable<Account> getAllAccounts(Account account) {
            List<Account> accounts = jdbcTemplate.query("select accountId,nickName,rewards,balance,customerId from account",(result,rowNum)-> new Account(result.getLong("accountId")
                    ,result.getString("nickName")
                    ,result.getInt("rewards")
                    ,result.getDouble("balance")
                    ,result.getLong("customerId"))
                    );
            return accounts;
        }
//get account by id
        public Account getAccountById(Long accountId){
            String query = "SELECT * FROM ACCOUNT WHERE ID = ?";
            Account account = jdbcTemplate.queryForObject(query,new Object[]{accountId},
                    new BeanPropertyRowMapper<>(Account.class));
            return account;

        }

//get account for customer
    public Account getAccountForCustomer(Long customerId,Long accountId){

            String query = "Select ACCOUNT FOR CUSTOMER WHERE ID = ?";
            Account account = jdbcTemplate.queryForObject(query,new Object[]{customerId,accountId},
                    new BeanPropertyRowMapper<>(Account.class));

            return account;

    }
//update account
    public Account updateAccount(Long id, String nickName, Integer rewards, Double balance, Long customerId){
            String query = "SELECT * FOR ACCOUNT WHERE ID = ?";
            Account account = jdbcTemplate.queryForObject(query,new Object[]{id,nickName,rewards,balance,customerId},
                    new BeanPropertyRowMapper<>(Account.class));
            return account;

    }
//add account
    public int addAccount(Long id, String nickName, Integer rewards, Double balance, Long customerId){
            String query = "INSERT INTO ACCOUNT VALUES(?,?,?)";
            return jdbcTemplate.update(query,id,balance,customerId,rewards);
    }
//delete account
    public int deleteAccount(Long id){
            String query = "DELETE FROM ACCOUNT WHERE ID = ?";
            return jdbcTemplate.update(query,id);
    }
//    @GetMapping( value = "/accounts")
//    public ResponseEntity<?> getAllAccounts(Account accounts){
//        List<Account> allAccounts = (List<Account>) accountService.getAllAccounts(accounts);
//        ResponseStatus rep = new ResponseStatus();
//        if(!allAccounts.isEmpty()){
//            rep.setCode(HttpStatus.OK.value());
//            rep.setData(allAccounts);
//            return new ResponseEntity<>(rep,HttpStatus.OK);
//        }else{
//            rep.setCode(HttpStatus.NOT_FOUND.value());
//            return new ResponseEntity<>(rep, HttpStatus.NOT_FOUND);
//        }
//    }

//    @GetMapping( value = "/accounts/{id}")
//    public ResponseEntity<?> getAccountById(@PathVariable Long id){
//        ResponseStatus rep = new ResponseStatus();
//        Optional<Account> acctt = accountService.getAccountById(id);
//        if(acctt.isPresent()){
//            rep.setCode(HttpStatus.OK.value());
//            rep.setData(acctt);
//            return new ResponseEntity<>(rep, HttpStatus.OK);
//        }else{
//            rep.setCode(HttpStatus.NOT_FOUND.value());
//            return new ResponseEntity<>(rep,HttpStatus.NOT_FOUND);
//        }
//    }
//
//    @GetMapping(value = "/customers/{id}/accounts")
//    public ResponseEntity<?> getAccountForCustomer(@PathVariable Long id){
//
//
//
//        Optional<Account> CustomerAccounts =  accountService.getAccountById(id);
//
//        ResponseStatus response = new ResponseStatus();
//        if(CustomerAccounts.isPresent()){
//            response.setCode(HttpStatus.OK.value());
//            response.setData(CustomerAccounts);
//            return new ResponseEntity<>(response,HttpStatus.OK);
//        }else{
//            response.setCode(HttpStatus.NOT_FOUND.value());
//            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
//        }
//    }

//    @PostMapping( value = "/customers/{id}/accounts")
//    public ResponseEntity<?> addAccount(@RequestBody Account account, @PathVariable Long id)
//    {
//        Account a = accountService.createAccountForCustomer(id, account);
//        if(a != null){
//            HttpHeaders responseHeaders = new HttpHeaders();
//            URI newAcctUri = ServletUriComponentsBuilder
//                    .fromCurrentRequestUri()
//                    .path("/{id}")
//                    .buildAndExpand(a.getId())
//                    .toUri();
//            responseHeaders.setLocation(newAcctUri);
//            return new ResponseEntity<>(null,responseHeaders,HttpStatus.CREATED);
//        }
//        else
//            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
//
//    }

//    @PutMapping( value = "/accounts/{id}")
//    public ResponseEntity<?> updateAccount(@Valid @RequestBody Account account, @PathVariable Long accountId) {
//      ResponseStatus responseStatus = new ResponseStatus();
//        accountService.updateAccount(account,accountId);
//      return new ResponseEntity<>(HttpStatus.CREATED,HttpStatus.OK);
//    }

//    @DeleteMapping( value = "/accounts/{id}")
//    public ResponseEntity<?> delAccount(@RequestBody Long id){
//        Response response = new Response();
//        accountService.deleteAccount(id);
//        return new ResponseEntity<>(response,HttpStatus.OK);
//    }
}
