package com.example.TBSBank.controllers;

import com.example.TBSBank.checkers.ResponseStatus;
import com.example.TBSBank.models.Customer;
import com.example.TBSBank.services.AccountService;
import com.example.TBSBank.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class CustomerController {

  @Autowired
  CustomerService customerService;

  @Autowired
  AccountService accountService;

  @GetMapping("/accounts/{accountId}/customer")
  public ResponseEntity<?> getCustomerByAccountId(@PathVariable Long accountId) {
    accountService.verifyAccountId(accountId, "Could not find account");
    Optional<Customer> customer = customerService.getCustomerByAccount(accountId);
    ResponseStatus response = new ResponseStatus();
    if (!customer.isPresent()) {
      response.setCode(HttpStatus.NOT_FOUND.value());
      response.setMessage("error fetching customer");
      return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    } else {
      response.setCode(HttpStatus.OK.value());
      response.setData(customer);
      return new ResponseEntity<>(response, HttpStatus.OK);
    }
  }

  @GetMapping("/customers")
  public ResponseEntity<?> getAllCustomers(){
    Iterable<Customer> customers = customerService.getAllCustomers();
    ResponseStatus response = new ResponseStatus();
    if(customers == null) {
      response.setCode(HttpStatus.NOT_FOUND.value());
      response.setMessage("error fetching customers");
      return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }else{
      response.setCode(HttpStatus.OK.value());
      response.setData(customers);
      return new ResponseEntity<>(response, HttpStatus.OK);
    }
  }

  @GetMapping("/customers/{id}")
  public ResponseEntity<?> getCustomerById(@PathVariable Long id) {
    customerService.verifyCustomerId(id, "Could not find customer");
    List<Customer> customer = customerService.getCustomerById(id);
    ResponseStatus response = new ResponseStatus();
    response.setCode(HttpStatus.OK.value());
    response.setData(customer.get(0));
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @PostMapping("/customers")
  public ResponseEntity<?> createCustomer(@Valid @RequestBody Customer customer) {
    Customer customer1 = customerService.createNewCustomer(customer);
    return new ResponseEntity<>(customer1, HttpStatus.OK);
  }

  @PutMapping("/customers/{customerId}")
  public ResponseEntity<?> updateCustomer(@RequestBody Customer customer, @PathVariable Long customerId) {
    customerService.verifyCustomerId(customerId, "Customer Account Not Found");
    Customer customer1 = customerService.updateCustomer(customerId, customer);
    ResponseStatus response = new ResponseStatus();
    if (customer1 == null) {
      response.setCode(HttpStatus.NOT_FOUND.value());
      response.setMessage("Error Creating Customer: Customer Not Found");
      return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    } else {
      response.setCode(HttpStatus.CREATED.value());
      response.setMessage("Successfully Updated Customer");
      response.setData(customer1);
      return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
  }

  @DeleteMapping("/customers/{customerId}")
  public ResponseEntity<?> deleteCustomer(@PathVariable Long customerId) {
    customerService.deleteCustomer(customerId);
    ResponseStatus response = new ResponseStatus();
    if (!customerService.getCustomerById(customerId).contains(customerId)) {
      response.setCode(HttpStatus.NOT_FOUND.value());
      response.setMessage("This Customer Does Not Exist!");
      return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    } else {
      customerService.deleteCustomer(customerId);
      return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }
  }

}
