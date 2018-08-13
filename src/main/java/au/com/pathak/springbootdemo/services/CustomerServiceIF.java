package au.com.pathak.springbootdemo.services;

import java.util.List;

import au.com.pathak.springbootdemo.model.Customer;

public interface CustomerServiceIF  {


  public List<Customer> getCustomerByFirstName(final String firstName) ;
  public List<Customer> getCustomerByLastName(final String lastName) ;
}
