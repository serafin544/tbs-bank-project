package com.example.TBSBank.models;

import javax.persistence.*;

@Entity
@Table(
  uniqueConstraints={@UniqueConstraint(columnNames={"street_number", "street_name", "city", "state", "zip", "customer_id"})}
)
public class Address {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ADDRESS_ID")
  private Long id;

  @Column(name = "STREET_NUMBER")
  private String streetNumber;

  @Column(name = "STREET_NAME")
  private String streetName;

  private String city;
  private String state;
  private String zip;
  private Long customer_id;

  public Address(){}

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getStreetNumber() {
    return streetNumber;
  }

  public void setStreetNumber(String streetNumber) {
    this.streetNumber = streetNumber;
  }

  public String getStreetName() {
    return streetName;
  }

  public void setStreetName(String streetName) {
    this.streetName = streetName;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getZip() {
    return zip;
  }

  public void setZip(String zip) {
    this.zip = zip;
  }

  public Long getCustomer_id() {
    return customer_id;
  }

  public void setCustomer_id(Long customer_id) {
    this.customer_id = customer_id;
  }

}
