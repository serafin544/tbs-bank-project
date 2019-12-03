package com.example.TBSBank.services;

import com.example.TBSBank.exceptions.ResourceNotFoundException;
import com.example.TBSBank.models.Account;
import com.example.TBSBank.models.Address;
import com.example.TBSBank.models.Customer;
import com.example.TBSBank.repository.AccountRepository;
import com.example.TBSBank.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CustomerService {

  @Autowired
  JdbcTemplate template;

  @Autowired
  CustomerRepository customerRepository;

  @Autowired
  AccountRepository accountRepository;


  Address address;

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

  public Iterable<Customer> getAllCustomers(){
    String query = "SELECT * FROM customer";
    return template.query(query, new BeanPropertyRowMapper<>(Customer.class));
  }


  public List<Customer> getCustomerById(Long customerId){
    String query = "SELECT * FROM CUSTOMER WHERE ID=?";
    return template.query(query, new Object[]{customerId},
            new BeanPropertyRowMapper<>(Customer.class));
  }

  public Customer createNewCustomer(Customer customer) {
    template.update("INSERT INTO CUSTOMER (first_name, last_name) VALUES(?,?)", customer.getFirstName(), customer.getLastName());

    Long customerId = template.queryForObject("SELECT MAX(id) From Customer", Long.class);
    customer.setId(customerId);
    // add addresses to address table
    customer.getAddress().forEach(address -> {
      template.update("INSERT INTO ADDRESS (street_number, street_name, city, state, zip, customer_id) VALUES(?,?,?,?,?, ?)", address.getStreetNumber(), address.getStreetName(),
        address.getCity(), address.getState(), address.getZip(), customer.getId());
    });

    template.queryForObject("SELECT * from customer where id = ?", new Object[]{customer.getId()}, new BeanPropertyRowMapper<>(Customer.class));
    List<Address> addresses = template.query("SELECT * FROM address where customer_id = ?", new Object[]{customer.getId()}, new BeanPropertyRowMapper<>(Address.class));

    addresses.forEach(address -> {
      // get ID for this address
      Long addressId = template.queryForObject("SELECT address_ID From Address WHERE street_number = ? AND street_name = ? AND city = ? AND state = ? AND zip = ? AND customer_id = ?", new Object[]{address.getStreetNumber(),
        address.getStreetName(), address.getCity(), address.getState(), address.getZip(), address.getCustomer_id()}, Long.class);
      address.setId(addressId);
    });

    // get addresses from address table where id = customer1.getId()
    // put addresses into customer1
    // return customer1;
//    String query = "SELECT * FROM customer WHERE id=?";
//    return template.queryForObject(query, new Object[]{customer.getId()}, new BeanPropertyRowMapper<>(Customer.class));
    customer.setAddress(Set.copyOf(addresses));
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
    template.update(sql, customerId);

  }

  public void verifyCustomerId(Long customerId, String message) throws ResourceNotFoundException {
        List<Customer> customer = getCustomerById(customerId);
        if(customer.isEmpty()) throw new ResourceNotFoundException(message);
  }



}
