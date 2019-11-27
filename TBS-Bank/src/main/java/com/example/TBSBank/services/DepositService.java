package com.example.TBSBank.services;

import com.example.TBSBank.models.Account;
import com.example.TBSBank.models.Deposit;
import com.example.TBSBank.repository.AccountRepository;
import com.example.TBSBank.repository.DepositRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DepositService {

    @Autowired
    JdbcTemplate template;
    @Autowired
    private DepositRepository depository;
    @Autowired
    private AccountRepository accountRepository;

    public List<Deposit> getAllDepositsForAccount(Long accountId) {
        List<Deposit> listOfDeposits = new ArrayList<>();
        Optional<Account> acc = accountRepository.findById(accountId);
        if (acc.isPresent()){
            //    listOfDeposits = depository.findAllByPayeeId(accountId);

              String query = "SELECT * FROM DEPOSIT WHERE accountId=?";
              List<Deposit> d = template.query(query, new Object[]{accountId}, new BeanPropertyRowMapper<>(Deposit.class));


            return d;
        }
        return listOfDeposits;
    }

    public Optional<Deposit> getDepositById(Long depositId) {
        String query = "SELECT * FROM DEPOSITS WHERE ID=?" ;
        Deposit deposit = template.queryForObject(query,new Object[]{depositId},
                new BeanPropertyRowMapper<>(Deposit.class));
        // depository.findById(depositId);
               Optional<Deposit> depositOptional = Optional.of(deposit);

        return depositOptional;
    }

    public Deposit addDeposit(Deposit deposit) {

        //depository.save(deposit);
              //  String query = "INSERT INTO DEPOSIT ";
            // int update - template.update(query,deposit);
             return depository.save(deposit);

       }

    public Deposit updateDeposit(Deposit deposit) {
        return depository.save(deposit);
    }

    public void deleteDeposit(Long depositId) {
        depository.deleteById(depositId);
    }
}
