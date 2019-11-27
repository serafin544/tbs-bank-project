package com.example.TBSBank.services;

import com.example.TBSBank.exceptions.ResourceNotFoundException;
import com.example.TBSBank.models.Account;
import com.example.TBSBank.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@Service
public class AccountService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    AccountRepository accountRepository;
    //get all accounts
    public Iterable<Account> getAllAccounts(Account account) {
        List<Account> acc;
        acc = jdbcTemplate.query("select id, nickName, rewards, balance, customer_id from account",(result,rowNum)-> new Account(result.getLong(account_id)
                ,result.getString("nickName")
                ,result.getInt("rewards")
                ,result.getDouble("balance")
                ,result.getLong("customer_id"))
        );
        return acc;
    }
    //get account by id
    public Account getAccountById(Long accountId){
        String query = "SELECT * FROM ACCOUNT WHERE ID = ?";
        Account acc = jdbcTemplate.queryForObject(query,new Object[]{accountId},
                new BeanPropertyRowMapper<>(Account.class));
        return acc;


//get all customer accounts
    public List<Account> getAllCustomerAccounts(Long customerID) {

        return accountRepository.findAllAccountsByCustomerID(customerID);

    }

    //get account for customer
    public Account getAccountForCustomer(Long customerId,Long id){

        String query = "Select ACCOUNT FOR CUSTOMER WHERE ID = ?";
        Account acc = jdbcTemplate.queryForObject(query,new Object[]{customerId,id},
                new BeanPropertyRowMapper<>(Account.class));

        return acc;

    }
    public Account updateAccount(Long id,Account account) {
        if (accountRepository.findById(id).isPresent()) {
            String query = "UPDATE account " +
                    "SET account_id ='" + account.getId() +
"'," +
                    "balance ='" + account.getBalance() +
"'," +
                    "customer_id='" + account.getCustomerId() + "'," +
                    "nickname ='" + account.getNickname() + "'," +
                    "rewards ='" + account.getRewards() + "'," +
                    "type ='" + account.getType() + "'," +


                    "WHERE id = ?";
            jdbcTemplate.update(query, id);
            return account;
        }
        return account;

    }
//    //update account
//    public void updateAccount(@Valid Account account, Long id, String nickname, Integer rewards, Double balance, Long customer_id){
//        String query = "UPDATE * FOR ACCOUNT WHERE ID = ?";
//        Account acc = jdbcTemplate.queryForObject(query,new Object[]{id,nickname,rewards,balance,customer_id},
//                new BeanPropertyRowMapper<>(Account.class));


    //add account
    public int addAccount(Long id, Account account, String nickName, Integer rewards, Double balance, Long customer_id){
        String query = "INSERT INTO ACCOUNT VALUES(?,?,?)";
        return jdbcTemplate.update(query,id,balance,customer_id,rewards,nickName);
    }
    //delete account
    public void deleteAccount(Long id){
        String query = "DELETE FROM ACCOUNT WHERE ID = ?";
        jdbcTemplate.update(query, id);
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
