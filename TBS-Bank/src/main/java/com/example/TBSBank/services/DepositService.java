package com.example.TBSBank.services;

import com.example.TBSBank.models.Account;
import com.example.TBSBank.models.Deposit;
import com.example.TBSBank.repository.AccountRepository;
import com.example.TBSBank.repository.DepositRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DepositService {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private DepositRepository depository;
    @Autowired
    private AccountRepository accountRepository;

    public List<Deposit> getAllDepositsForAccount(Long accountId) {
        List<Deposit> listOfDeposits = new ArrayList<>();
        Optional<Account> acc = accountRepository.findById(accountId);
        if (acc.isPresent()){
            listOfDeposits = depository.findAllByPayeeId(accountId);

            return listOfDeposits;
        }
        return listOfDeposits;
    }

    public Optional<Deposit> getDepositById(Long depositId) {
        return depository.findById(depositId);
    }

    public Deposit addDeposit(Deposit deposit) { return depository.save(deposit); }

    public Deposit updateDeposit(Deposit deposit) {
        return depository.save(deposit);
    }

    public void deleteDeposit(Long depositId) {
        depository.deleteById(depositId);
    }
}

