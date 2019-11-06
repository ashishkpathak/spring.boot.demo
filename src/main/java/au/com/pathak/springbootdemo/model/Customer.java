package au.com.pathak.springbootdemo.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Version;

@Entity
public class Customer implements Serializable {

  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long id;
  private String firstName;
  private String lastName;

  @OneToMany
  @JoinColumn(name = "device_id")
  private Set<Device> deviceSet = new HashSet<>();

  @Version
  private int version;



  public Customer(){

  }

  public Customer(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public long getCustomerId() {
    return id;
  }

  public void setCustomerId(long id) {
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

  public Set<Device> getDeviceSet(){
    return deviceSet;
  }
}
