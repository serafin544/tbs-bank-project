package com.example.TBSBank.repository;

import com.example.TBSBank.models.Deposit;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DepositRepository extends CrudRepository<Deposit, Long>{
    List<Deposit> findAllByPayeeId(Long accountId);
}
