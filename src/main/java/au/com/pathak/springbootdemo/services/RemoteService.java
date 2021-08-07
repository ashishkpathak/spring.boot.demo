package au.com.pathak.springbootdemo.services;

import java.util.Arrays;
import java.util.List;

import au.com.pathak.springbootdemo.exceptions.CustomerNotFoundException;
import au.com.pathak.springbootdemo.model.Customer;
import au.com.pathak.springbootdemo.model.Device;
import com.google.common.collect.Lists;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RemoteService implements RemoteServiceIF {

  @Autowired
  private CustomerServiceIF customerServiceIF;

  @HystrixCommand(fallbackMethod = "getLocalCustomerByFirstName")
  public List<Customer> getRemoteCustomerByFirstName(final String firstName) {
    Customer customer = new Customer(1L,"RemoteUserFirstName", "RemoteUserLastName");
    customer.getDeviceSet().add(new Device());
    return Arrays.asList(customer);
  }

  @HystrixCommand(fallbackMethod = "getLocalCustomerByLastName")
  public List<Customer> getRemoteCustomerByLastName(final String lastName) {

    throw new CustomerNotFoundException(lastName);

  }

  public List<Customer> getLocalCustomerByLastName(final String lastName) {
    return customerServiceIF.getCustomerByLastName(lastName);
  }

  public List<Customer> getLocalCustomerByFirstName(final String firstName) {
    return customerServiceIF.getCustomerByFirstName(firstName);
  }
}
