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
      String query = "SELECT * FROM customer WHERE ID=?";
      //customer = customerRepository.findById(account.get().getAccountId());
      Customer c  = template.queryForObject(query, new Object[]{accountId},
              new BeanPropertyRowMapper<>(Customer.class));
      customer = Optional.of(c);
      return customer;
    }
    return customer;
  }

  public Iterable<Customer> getAllCustomers() {
    String query = "SELECT * FROM customer";
    return template.query(query, new Object[]{}, new BeanPropertyRowMapper<>(Customer.class));
  }

  public Optional<Customer> getCustomerById(Long customerId){
    String query = "SELECT * FROM customer WHERE ID=?";
    Customer customer = template.queryForObject(query,new Object[]{customerId},
            new BeanPropertyRowMapper<>(Customer.class));
      Optional<Customer> customerOptional = Optional.of(new Customer());
      customerOptional.equals(customer);
    //customerRepository.findById(customerId);
    return customerOptional;
  }

  public Customer createNewCustomer(Customer customer){
      String query = "INSERT INTO customer VALUES(?,?,?,?)";
      template.update(query,customer.getCustomer_id(),customer.getFirstName(),customer.getLastName(),customer.getAddress());

      String  query2 = "INSERT INTO address VALUES(?,?,?,?,?,?)";
      template.update(query2,customer.getAddress().getId(),
              customer.getAddress().getStreetNumber(),
              customer.getAddress().getStreetName(),
              customer.getAddress().getCity(),
              customer.getAddress().getState(),
              customer.getAddress().getZip());


    return customer;
  }

  public Customer updateCustomer(Long customerId, Customer customer){
    if(customerRepository.findById(customerId).isPresent()){
      String query = "UPDATE CUSTOMER " +
              "SET first_name ='" + customer.getFirstName() + "'," +
              "last_name ='" + customer.getLastName() + "'," +
              " address ='" + customer.getAddress() + "'," +
              " WHERE id = ?";
      template.update(query, customerId);
      return customer;
    }
    return null;

  }

  public void deleteCustomer(Long customerId) {
    String sql = "DELETE FROM CUSTOMER WHERE ID=?";
    template.update(sql,customerId);

    //customerRepository.deleteById(customerId);
  }

  public void verifyCustomerId(Long customerId, String message) throws ResourceNotFoundException {
        Optional<Customer> customer = getCustomerById(customerId);
        if(!customer.isPresent())
        throw new ResourceNotFoundException(message);
        }
        }
