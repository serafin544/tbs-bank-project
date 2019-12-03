package com.example.TBSBank.models;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Customer {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "CUSTOMER_ID")
  private Long customer_id;

  @Column(name = "first_name")
  private String firstName;
  @Column(name="last_name")
  private String lastName;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "CUSTOMER_ID")
  private Address address;

  public Customer() {
  }

  public Customer(Long customer_id, String firstName, String lastName, Address address) {
    this.customer_id = customer_id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.address = address;
  }

  public Long getCustomer_id() {
    return customer_id;
  }

  public void setCustomer_id(Long customer_id) {
    this.customer_id = customer_id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  @Override
  public String toString() {
    return "Customer{" +
            "customer_id=" + customer_id +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", address=" + address +
            '}';
  }
}