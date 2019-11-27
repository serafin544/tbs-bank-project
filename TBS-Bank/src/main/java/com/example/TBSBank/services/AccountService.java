package com.example.TBSBank.services;

import com.example.TBSBank.exceptions.ResourceNotFoundException;
import com.example.TBSBank.models.Account;
import com.example.TBSBank.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;


@Service
public class AccountService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    AccountRepository accountRepository;
    //get all accounts
    public Iterable<Account> getAllAccounts(Account account) {
        List<Account> acc = jdbcTemplate.query("id,nickName,rewards,balance,customer_id from account",(result,rowNum)-> new Account(result.getLong("accountId")
                ,result.getString("nickName")
                ,result.getInt("rewards")
                ,result.getDouble("balance")
                ,result.getLong("customerId"))
        );
        return acc;
    }
    //get account by id
    public Account getAccountById(Long accountId){
        String query = "SELECT * FROM ACCOUNT WHERE ID = ?";
        Account acc = jdbcTemplate.queryForObject(query,new Object[]{accountId},
                new BeanPropertyRowMapper<>(Account.class));
        return acc;

    }

    //get account for customer
    public Account getAccountForCustomer(Long customerId,Long id){

        String query = "Select ACCOUNT FOR CUSTOMER WHERE ID = ?";
        Account acc = jdbcTemplate.queryForObject(query,new Object[]{customerId,id},
                new BeanPropertyRowMapper<>(Account.class));

        return acc;

    }
    //update account
    public Account updateAccount(@Valid Account account, Long id, String nickName, Integer rewards, Double balance, Long customerId){
        String query = "SELECT * FOR ACCOUNT WHERE ID = ?";
        Account acc = jdbcTemplate.queryForObject(query,new Object[]{id,nickName,rewards,balance,customerId},
                new BeanPropertyRowMapper<>(Account.class));
        return account;

    }
    //add account
    public int addAccount(Long id, Account account, String nickName, Integer rewards, Double balance, Long customerId){
        String query = "INSERT INTO ACCOUNT VALUES(?,?,?)";
        return jdbcTemplate.update(query,id,balance,customerId,rewards,nickName);
    }
    //delete account
    public int deleteAccount(Long id){
        String query = "DELETE FROM ACCOUNT WHERE ID = ?";
        return jdbcTemplate.update(query,id);
    }
////verify account
//    public Account verifyAccountId(Long accountId, String s){
//
//        String query = "SELECT USERNAME FROM ACCOUNT WHERE ID = ?";
//
//    }
////get all accounts
//    public Iterable<Account> getAllAccounts(Account accounts){
//        return accountRepository.findAll();
//    }
//
////get all customer accounts
//    public List<Account> getAllCustomerAccounts(Long customerID) {
//        return accountRepository.findAllAccountsByCustomerId(customerID);
//    }
//
////get account by id
//
//    public Optional<Account> getAccountById(Long id){
//        return accountRepository.findById(id);
//    }
//
////create account for customer
//    public Account createAccountForCustomer(Long customerId, Account account) {
//        account.setCustomer(customerId);
//
//        return accountRepository.save(account);
//    }
//
////update customer
//    public Account updateAccount(Account account, Long accountId) {
//        account.setId(accountId);
//        return accountRepository.save(account);
//    }
//
////delete account
//    public void deleteAccount(Long accountId) {
//
//        accountRepository.deleteById(accountId);
//    }
//
    //verify account
  public void verifyAccountId(Long accountId, String message) throws ResourceNotFoundException {
    Optional<Account> account = Optional.ofNullable(getAccountById(accountId));

    if(!account.isPresent())
      throw new ResourceNotFoundException(message);
  }

}
