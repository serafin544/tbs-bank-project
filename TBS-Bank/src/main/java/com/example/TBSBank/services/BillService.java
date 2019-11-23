package com.example.TBSBank.services;

import com.example.TBSBank.models.Bill;
import com.example.TBSBank.repository.BillRepository;
import com.example.TBSBank.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BillService {

    @Autowired
    private BillRepository billRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private BillService billService;

    public List<Bill> getAllBillsForAccount(Long accountId) {
        return billRepository.findAllByAccountId(accountId);
    }

    public Optional<Bill> getBillById(Long id) {
        return billRepository.findById(id);
    }

    public List<Bill> getAllBillsForCustomer(Long customerId) {
        return billRepository.findAllByCustomerId(customerId);
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
