package com.example.TBSBank.repository;

import com.example.TBSBank.models.Account;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AccountRepository extends CrudRepository<Account, Long>  {

    Account getAccountById(Long id);
    List<Account> findAllAccountsByCustomerId(Long customerId);

}
