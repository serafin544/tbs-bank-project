package com.example.TBSBank.services;

import com.example.TBSBank.exceptions.ResourceNotFoundException;
import com.example.TBSBank.models.Account;
import com.example.TBSBank.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;
//get all accounts
    public Iterable<Account> getAllAccounts(Account accounts){
        return accountRepository.findAll();
    }

//get all customer accounts
    public List<Account> getAllCustomerAccounts(Long customerID) {
        return accountRepository.findAllAccountsByCustomerId(customerID);
    }

//get account by id

    public Optional<Account> getAccountById(Long id){
        return accountRepository.findById(id);
    }
//create account for customer
    public Account createAccountForCustomer(Long customerId, Account account) {
        account.setCustomer(customerId);

        return accountRepository.save(account);
    }

//update customer
    public Account updateAccount(Account account, Long accountId) {
        account.setId(accountId);
        return accountRepository.save(account);
    }

//delete account
    public void deleteAccount(Long accountId) {

        accountRepository.deleteById(accountId);
    }

    //verify account
  public void verifyAccountId(Long accountId, String message) throws ResourceNotFoundException {
    Optional<Account> account = getAccountById(accountId);

    if(!account.isPresent())
      throw new ResourceNotFoundException(message);
  }

}
