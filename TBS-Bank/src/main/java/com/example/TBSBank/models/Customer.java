package com.example.TBSBank.models;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Customer {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "CUSTOMER_ID")
  private Long id;

  @Column(name = "first_name")
  private String firstName;
  @Column(name="last_name")
  private String lastName;

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "CUSTOMER_ID")
  private Set<Address> address;

  public Customer() {
  }

  public Customer(long id, String first_name, String last_name, Object address){

  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

  public Set<Address> getAddress() {
    return address;
  }

  public void setAddress(Set<Address> address) {
    this.address = address;
  }

  @Override
  public String toString() {
    return "Customer{" +
      "id=" + id +
      ", firstName='" + firstName + '\'' +
      ", lastName='" + lastName + '\'' +
      ", address=" + address +
      '}';
  }
}
