package com.example.TBSBank.services;

import com.example.TBSBank.exceptions.ResourceNotFoundException;
import com.example.TBSBank.models.Account;
import com.example.TBSBank.models.Customer;
import com.example.TBSBank.repository.AccountRepository;
import com.example.TBSBank.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

  @Autowired
  CustomerRepository customerRepository;

  @Autowired
  AccountRepository accountRepository;

  public Optional<Customer> getCustomerByAccount(Long accountId){
    Optional<Customer> customer = Optional.empty();
    Optional<Account> account = accountRepository.findById(accountId);
    if(account.isPresent()){
      customer = customerRepository.findById(account.get().getId());

      return customer;
    }
    return customer;
  }

  public List<Customer> getAllCustomers() {
    return customerRepository.findAll();
  }

  public Optional<Customer> getCustomerById(Long customerId){
    return customerRepository.findById(customerId);
  }

  public Customer createNewCustomer(Customer customer){
    return customerRepository.save(customer);
  }

  public Customer updateCustomer(Long customerId, Customer customer){
    customer.setId(customerId);
    return customerRepository.save(customer);
  }

  public void deleteCustomer(Long customerId) {
    customerRepository.deleteById(customerId);
  }

  public void verifyCustomerId(Long customerId, String message) throws ResourceNotFoundException {
    Optional<Customer> customer = getCustomerById(customerId);
    if(!customer.isPresent())
      throw new ResourceNotFoundException(message);
  }
}
