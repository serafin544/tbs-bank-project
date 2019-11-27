package com.example.TBSBank.services;

import com.example.TBSBank.models.Withdraw;
import com.example.TBSBank.repository.AccountRepository;
import com.example.TBSBank.repository.WithdrawRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WithdrawService {

    @Autowired
    JdbcTemplate template;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    WithdrawRepository withdrawRepository;


    public List<Withdraw> getAllWithdrawsForAccount(Long accountId) {
        if(accountRepository.findById(accountId).isPresent()){
            String query = "SELECT * FROM DEPOSIT WHERE accountId=?";
            List<Withdraw> w = template.query(query, new Object[]{accountId}, new BeanPropertyRowMapper<>(Withdraw.class));


            return w;
        }
        else
            return null;
    }

    public Optional<Withdraw> getWithdrawById(Long id) {
      //  return withdrawRepository.findById(id);
        String query = "SELECT * FROM DEPOSITS WHERE ID=?" ;
        Withdraw w = template.queryForObject(query,new Object[]{id},
                new BeanPropertyRowMapper<>(Withdraw.class));
        // depository.findById(depositId);
        Optional<Withdraw> withdraw = Optional.of(w);

        return withdraw;

    }

    public Withdraw createWithdraw(Withdraw withdraw, Long accountId) {
        if(accountRepository.findById(accountId).isPresent()){
            String query = "INSERT INTO WITHDRAW VALUES(?,?,?,?,?,?,?,?)";
            template.update(query,withdraw.getId(),withdraw.getStatus(),withdraw.getMedium(),withdraw.getType(),
                    withdraw.getTransaction_date(),withdraw.getPayerId(),withdraw.getAmount(),withdraw.getDescription());


            return withdraw;

        }
        return null;
    }

    public Withdraw updateWithdraw(Long accountId, Withdraw withdraw) {
        if(accountRepository.findById(accountId).isPresent()){
            String query = "UPDATE Withdraw " +
                    "SET id ='"  + withdraw.getId() + "'," +
                    "status ='" + withdraw.getStatus() + "'," +
                    "medium ='" + withdraw.getMedium() + "'," +
                    "type ='" + withdraw.getType() + "'," +
                    "transaction_date ='" + withdraw.getTransaction_date() + "'," +
                    "payerId ='" + withdraw.getPayerId()+ "'," +
                    "amount ='" + withdraw.getAmount() + "'," +
                    "description ='" + withdraw.getDescription() + "'," +
                    " WHERE id = ?";
            template.update(query, accountId);
            return withdraw;
        }
        return null;
    }

    public void deleteWithdrawId(Long withdrawId) {
        /*withdrawRepository.findById(withdrawId);*/


        String sql = "DELETE FROM WITHDRAW WHERE ID=?";
        template.update(sql,withdrawId);

    }
}
