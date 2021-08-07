package au.com.pathak.springbootdemo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Device {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private int id;

  @Column(name = "name")
  private String name;
//
//  @ManyToOne()
////  @JoinColumn(name = "customer_id", referencedColumnName = "id", nullable = false)
//  private Customer customer;

//
//  public void setCustomer(Customer customer){
//    this.customer = customer;
//  }
  public void setName(String name) {
    this.name = name;
  }
}
