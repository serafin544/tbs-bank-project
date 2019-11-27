package com.example.TBSBank.services;

import com.example.TBSBank.exceptions.ResourceNotFoundException;
import com.example.TBSBank.models.Account;
import com.example.TBSBank.models.Customer;
import com.example.TBSBank.repository.AccountRepository;
import com.example.TBSBank.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

  @Autowired
  JdbcTemplate template;
  @Autowired
  CustomerRepository customerRepository;

  @Autowired
  AccountRepository accountRepository;

  public Optional<Customer> getCustomerByAccount(Long accountId){
    Optional<Customer> customer = Optional.empty();
    Optional<Account> account = accountRepository.findById(accountId);
    if(account.isPresent()){
      String query = "SELECT * FROM CUSTOMER WHERE ID=?";
      //customer = customerRepository.findById(account.get().getAccountId());
      Customer c  = template.queryForObject(query, new Object[]{accountId},
              new BeanPropertyRowMapper<>(Customer.class));
      customer = Optional.of(c);
      return customer;
    }
    return customer;
  }

  public Iterable<Customer> getAllCustomers() {
    List<Customer> customers = template.query("select from id , first_name , last_name , address from customer",
            (result,rowNum)-> new Customer(result.getLong("id"),
                    result.getString("first_name"),
                    result.getString("last_name"),
                    result.getObject("address")));

    return customers;
  }

  public Optional<Customer> getCustomerById(Long customerId){
    String query = "SELECT * FROM CUSTOMER WHERE ID=?";
    Customer customer = template.queryForObject(query,new Object[]{customerId},
            new BeanPropertyRowMapper<>(Customer.class));
      Optional<Customer> customerOptional = Optional.of(new Customer());
      customerOptional.equals(customer);
    //customerRepository.findById(customerId);
    return customerOptional;
  }

  public Customer createNewCustomer(Customer customer){
      String query = "INSERT INTO CUSTOMEER VALUES(?,?,?,?)";
      int  updater = template.update(query,customer);
    return customerRepository.save(customer);
  }

  public Customer updateCustomer(Long customerId, Customer customer){
    customer.setId(customerId);
    String query = "UPDATE CUSTOMER SET id=? , first_name=? , last_name=? address=?";
    template.execute(query);
    return customerRepository.save(customer);
  }

  public void deleteCustomer(Long customerId) {
    String sql = "DELETE FROM CUSTOMER WHERE ID=?";
    template.execute(sql);

    //customerRepository.deleteById(customerId);
  }

  public void verifyCustomerId(Long customerId, String message) throws ResourceNotFoundException {
        Optional<Customer> customer = getCustomerById(customerId);
        if(!customer.isPresent())
        throw new ResourceNotFoundException(message);
        }
        }
