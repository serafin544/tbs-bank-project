package com.example.TBSBank.services;

import com.example.TBSBank.exceptions.ResourceNotFoundException;
import com.example.TBSBank.models.Account;
import com.example.TBSBank.models.Address;
import com.example.TBSBank.models.Customer;
import com.example.TBSBank.repository.AccountRepository;
import com.example.TBSBank.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CustomerService {

  @Autowired
  JdbcTemplate template;

  @Autowired
  CustomerRepository customerRepository;

  @Autowired
  AccountRepository accountRepository;



  Logger logger = LoggerFactory.getLogger(CustomerService.class);


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
    String query = "SELECT customer_id FROM customer";
    List<Long> customerIds = template.queryForList(query, Long.class);
    List<Customer> customers = new ArrayList<>();
    customerIds.forEach(id -> {
        Customer customer = this.getCustomerById(id);
        customer.setId(id);
        customers.add(customer);
    });
    return customers;
  }

  public Customer getCustomerById(Long customerId){

    String query = "SELECT * FROM customer WHERE customer_id=?";
    Customer customer = template.queryForObject(query,new Object[]{customerId},
            new BeanPropertyRowMapper<>(Customer.class));
    //customerRepository.findById(customerId);
      Long addressId = template.queryForObject("SELECT ADDRESS_ID FROM CUSTOMER WHERE customer_id = ?", new Object[]{customerId}, Long.class);
      Address address = template.queryForObject("SELECT * FROM ADDRESS WHERE ADDRESS_ID = ?", new Object[]{addressId}, new BeanPropertyRowMapper<>(Address.class));
      address.setId(addressId);
      customer.setAddress(address);
      customer.setId(customerId);
      logger.info(customer.getAddress().toString());
      logger.info(address.toString());
      return customer;
    }

  public Customer createNewCustomer(Customer customer){
      template.update("INSERT INTO CUSTOMER (first_name, last_name) VALUES(?,?)", customer.getFirstName(), customer.getLastName());
      Long customerId = template.queryForObject("SELECT MAX(customer_id) From Customer", Long.class);
      customer.setId(customerId);
      logger.info(customer.getAddress().toString());
      Address a = customer.getAddress();
      // add addresses to address table
      template.update("INSERT INTO ADDRESS (street_number, street_name, city, state, zip) VALUES(?,?,?,?,?)",
              a.getStreetNumber(), a.getStreetName(),
              a.getCity(), a.getState(), a.getZip());
      Long addressId = template.queryForObject(
              "SELECT address_ID From Address WHERE street_number = ? AND street_name = ? AND city = ? AND state = ? AND zip = ?",
              new Object[]{a.getStreetNumber(), a.getStreetName(), a.getCity(),
                      a.getState(), a.getZip()}, Long.class);
      template.update("UPDATE CUSTOMER SET address_id = ? WHERE customer_id = ?", addressId, customerId);
      a.setId(addressId);
      logger.info(a.toString());
      logger.info(customer.toString());

      customer.setAddress(a);
     /* customer.getAddress().forEach(address -> {
          template.update("INSERT INTO ADDRESS (street_number, street_name, city, state, zip, customer_id) VALUES(?,?,?,?,?, ?)", address.getStreetNumber(), address.getStreetName(),
                  address.getCity(), address.getState(), address.getZip(), customer.getId());
      });
      // searches for generated customer
      // template.queryForObject("SELECT * from customer where id = ?", new Object[]{customer.getId()}, new BeanPropertyRowMapper<>(Customer.class));
      List<Address> addresses = template.query("SELECT * FROM address where customer_id = ?", new Object[]{customer.getId()}, new BeanPropertyRowMapper<>(Address.class));


      addresses.forEach(address -> {
          // get ID for this address
          Long addressId = template.queryForObject(
                  "SELECT address_ID From Address WHERE street_number = ? AND street_name = ? AND city = ? AND state = ? AND zip = ? AND customer_id = ?",
                  new Object[]{address.getStreetNumber(), address.getStreetName(), address.getCity(),
                          address.getState(), address.getZip(), address.getCustomer_id()}, Long.class);
          address.setId(addressId);
      });
      // put addresses into customer1
      customer.setAddress(Set.copyOf(addresses));*/
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

  }

  public boolean verifyCustomerId(Long customerId)  {

        List<Customer> customers = template.query("SELECT * FROM CUSTOMER WHERE ID = ?", new Object[]{customerId}, new BeanPropertyRowMapper<>(Customer.class));

        if(!customers.isEmpty() ) {
            Customer customer = getCustomerById(customerId);
            return true;
        }else{
            return false;
        }
  }
}


/*template.update("INSERT INTO customer (first_name, last_name) VALUES(?,?)", customer.getFirstName(), customer.getLastName());
      Long customerId = template.queryForObject("SELECT MAX(id) From Customer", Long.class);
      customer.setId(customerId);

      // add addresses to address table
      customer.getAddress().forEach(address1 -> {
    template.update("INSERT INTO ADDRESS(street_number, street_name, city, state, zip, customer_id) VALUES(?,?,?,?,?,?)",new Object[]{
            address.getStreetNumber(), address.getStreetName(), address.getCity(), address.getState(), address.getZip(), address.getCustomer_id(), Address.class});
  });

    template.queryForObject("SELECT * FROM CUSTOMER WHERE id = ?", new Object[]{customer.getId()}, new BeanPropertyRowMapper<>(Customer.class));
    List<Address> addresses = template.query("SELECT * FROM ADDRESS WHERE id = ?", new Object[]{customer.getId()}, new BeanPropertyRowMapper<>(Address.class));
    addresses.forEach(address1 -> {
      // get ID for this address
      Long addressId = template.queryForObject(
              "SELECT address_Id From Address Where street_name = ? AND street_number = ? AND city = ? AND state = ? AND zip = ? AND customer_id = ?",
              new Object[]{address.getStreetName(), address.getStreetNumber(), address.getCity(), address.getState(), address.getZip(), address.getCustomer_id()}, Long.class);

      address.setId(addressId);
    });

    customer.setAddress(Set.copyOf(addresses));

     String query = "INSERT INTO customer VALUES (?,?,?,?)";
     template.update(query,customer.getId(),customer.getFirstName(),customer.getLastName(),customer.getAddress());
      template.update("INSERT INTO ADDRESS(street_number, street_name, city, state, zip, customer_id) VALUES(?,?,?,?,?,?) WHERE customer_id=?" ,new Object[]{
              address.getStreetNumber(), address.getStreetName(), address.getCity(), address.getState(), address.getZip(), customer.getId(), Address.class});
*/
