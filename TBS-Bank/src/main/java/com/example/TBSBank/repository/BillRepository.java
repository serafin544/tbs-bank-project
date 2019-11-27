package com.example.TBSBank.repository;

import com.example.TBSBank.models.Bill;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface BillRepository extends CrudRepository<Bill, Long>{
    List<Bill> findAllByAccountId(Long accountId);
}
