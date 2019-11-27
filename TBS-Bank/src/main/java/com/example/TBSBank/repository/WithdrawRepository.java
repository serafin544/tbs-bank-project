package com.example.TBSBank.repository;

import com.example.TBSBank.models.Withdraw;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WithdrawRepository extends CrudRepository<Withdraw, Long> {
    List<Withdraw> findAllByPayerId(Long accountId);
}
