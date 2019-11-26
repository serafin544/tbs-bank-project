package com.example.TBSBank.services;

import com.example.TBSBank.models.Account;
import com.example.TBSBank.models.Bill;
import com.example.TBSBank.models.Customer;
import com.example.TBSBank.repository.AccountRepository;
import com.example.TBSBank.repository.BillRepository;
import com.example.TBSBank.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class BillService {

    @Autowired
    private BillRepository billRepository;
    @Autowired
      private AccountRepository accountRepository;
    @Autowired
      private CustomerRepository customerRepository;
    @Autowired
    private CustomerService customerService;

    public List<Bill> getAllBillsForAccount(Long accountId) {
        return billRepository.findAllByAccountId(accountId);
    }

    public Optional<Bill> getBillById(Long id) {
        return billRepository.findById(id);
    }

    public List<Bill> getAllBillsForCustomer(Long customerId) {
      List<Account> accounts = accountRepository.findAllAccountsByCustomerId(customerId);
      Account a = accounts.get(0);
      return billRepository.findAllByAccountId(a.getId());
    }

    public Bill createBill(Bill bill, Long accountId) {
        if (accountRepository.findById(accountId).isPresent())
            return billRepository.save(bill);
        return null;
    }

    public Bill updateBill(Long id, Bill bill) {
        if (billRepository.findById(id).isPresent())
            return billRepository.save(bill);
        return null;
    }

    public void deleteBill(Long id) {
        billRepository.deleteById(id);
    }
}
