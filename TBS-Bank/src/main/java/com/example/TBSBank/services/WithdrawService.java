package com.example.TBSBank.services;

import com.example.TBSBank.models.Withdraw;
import com.example.TBSBank.repository.WithdrawRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class WithdrawService {

    @Autowired
    WithdrawRepository withdrawRepository;


    public List<Withdraw> getAllWithdrawsForAccount(Long accountId) {
        return withdrawRepository.findAllByPayerId(accountId);
    }

    public Optional<Withdraw> getWithdrawById(Long id) {
        return withdrawRepository.findById(id);
    }

    public Withdraw createWithdraw(Withdraw withdraw, Long withdrawId) {
        withdraw.setPayerId( withdrawId );
        return withdrawRepository.save( withdraw );
    }

    public Withdraw updateWithdraw(Long id, Withdraw withdraw) {
        withdraw.setId(id);
        return withdrawRepository.save(withdraw);
    }

    public void deleteWithdrawId(Long withdrawId) {
        withdrawRepository.findById(withdrawId);
    }
}
