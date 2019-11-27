package com.example.TBSBank.services;

import com.example.TBSBank.models.Account;
import com.example.TBSBank.models.Bill;
import com.example.TBSBank.repository.AccountRepository;
import com.example.TBSBank.repository.BillRepository;
import com.example.TBSBank.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BillService {

    @Autowired
    private BillRepository billRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private JdbcTemplate template;

    public List<Bill> getAllBillsForAccount(Long accountId) {
        String query = "SELECT * FROM bill WHERE account_id=?";
        return template.query(query, new Object[]{accountId}, new BeanPropertyRowMapper<>(Bill.class));
    }

    public Bill getBillById(Long id) {
        String query = "SELECT * FROM bill WHERE id=?";
        return template.queryForObject(query, new Object[]{id}, new BeanPropertyRowMapper<>(Bill.class));
    }

    public List<Bill> getAllBillsForCustomer(Long customerId) {
        List<Account> accounts = accountRepository.findAllAccountsByCustomerID(customerId);
        Account a = accounts.get(0);
        String query = "SELECT * FROM bill WHERE account_id=?";
        return template.query(query, new Object[]{a.getAccountId()}, new BeanPropertyRowMapper<>(Bill.class));
    }

    public Bill createBill(Bill bill, Long accountId) {
        if (accountRepository.findById(accountId).isPresent()){
            String query = "INSERT INTO bill VALUES (?,?,?,?,?,?,?,?,?,?)";
            template.update(query, bill.getId(), bill.getAccountId(), bill.getCreationDate(), bill.getNickname(), bill.getPayee(),
                    bill.getPaymentAmount(), bill.getPaymentDate(), bill.getRecurringDate(), bill.getStatus(), bill.getUpcomingPaymentDate());
            return bill;
        }
        return null;
    }

    public Bill updateBill(Long id, Bill bill) {
        if (billRepository.findById(id).isPresent()){
            String query = "UPDATE bill " +
                    "SET account_id ='" + bill.getAccountId() + "'," +
                    "creation_date ='" + bill.getCreationDate() + "'," +
                    "nickname ='" + bill.getNickname() + "'," +
                    "payee ='" + bill.getPayee() + "'," +
                    "payment_amount ='" + bill.getPaymentAmount() + "'," +
                    "payment_date ='" + bill.getPaymentDate() + "'," +
                    "recurring_date ='" + bill.getRecurringDate() + "'," +
                    "status ='" + bill.getStatus() + "'," +
                    "upcoming_payment_date ='" + bill.getUpcomingPaymentDate() + "'" +
                    " WHERE id = ?";
            template.update(query, id);
            return bill;
        }
        return null;
    }

    public void deleteBill(Long id) {
        String query = "DELETE FROM bill WHERE ID = ?";
        template.update(query, id);
    }
}
