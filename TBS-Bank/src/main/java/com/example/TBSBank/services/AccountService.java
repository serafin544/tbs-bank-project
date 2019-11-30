package com.example.TBSBank.services;

import com.example.TBSBank.exceptions.ResourceNotFoundException;
import com.example.TBSBank.models.Account;
import com.example.TBSBank.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
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
    private AccountRepository accountRepository;

    public Iterable<Account> getAllAccounts(Account account) {
        String query = "SELECT * FROM account";
        return jdbcTemplate.query(query, new Object[]{}, new BeanPropertyRowMapper<>(Account.class));
    }

    //get account by id
    public Account getAccountById(Long accountId) {
        String query = "SELECT * FROM ACCOUNT WHERE ID = ?";
        Account acc = jdbcTemplate.queryForObject(query, new Object[]{accountId}, new BeanPropertyRowMapper<>(Account.class));
        return acc;
    }

    //get account for customer
    public Account getAccountForCustomer (Long customerId, Long id){
        String query = "Select ACCOUNT FOR CUSTOMER WHERE ID = ?";
        Account acc = jdbcTemplate.queryForObject(query, new Object[]{customerId, id}, new BeanPropertyRowMapper<>(Account.class));
        return acc;
    }
    public Account updateAccount (Long id, Account account){
        if (accountRepository.findById(id).isPresent()) {
            String query = "UPDATE account " +
                    "SET account_id ='" + account.getId() + "'," +
                    "balance ='" + account.getBalance() + "'," +
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

    public Account addAccount (Long id, Account account){
        String query = "INSERT INTO ACCOUNT VALUES(?,?,?,?,?,?)";
        jdbcTemplate.update(query, id, account.getType(), account.getNickname(), account.getRewards(), account.getBalance(), account.getCustomerId());
        return account;
    }

    public void deleteAccount (Long id){
        String query = "DELETE FROM ACCOUNT WHERE ID = ?";
        jdbcTemplate.update(query, id);
    }
    public void verifyAccountId (Long accountId, String message) throws ResourceNotFoundException {
        Optional<Account> account = Optional.ofNullable(getAccountById(accountId));

        if (!account.isPresent())
            throw new ResourceNotFoundException(message);
    }
}

