package au.com.pathak.springbootdemo.services;

import java.rmi.Remote;
import java.util.Arrays;
import java.util.List;

import au.com.pathak.springbootdemo.exceptions.CustomerNotFoundException;
import au.com.pathak.springbootdemo.model.Customer;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RemoteService implements RemoteServiceIF {

  // @Autowired
  // private RestTemplate template;

  @Autowired
  private CustomerServiceIF customerServiceIF;

  @HystrixCommand(fallbackMethod = "getLocalCustomerByFirstName")
  public List<Customer> getRemoteCustomerByFirstName(final String firstName) {

    return Arrays.asList(new Customer());
  }

  @HystrixCommand(fallbackMethod = "getLocalCustomerByLastName")
  public List<Customer> getRemoteCustomerByLastName(final String firstName) {

    throw new CustomerNotFoundException(firstName);

  }

  public List<Customer> getLocalCustomerByLastName(final String lastName) {
    return customerServiceIF.getCustomerByLastName(lastName);
  }

  public List<Customer> getLocalCustomerByFirstName(final String firstName) {
    return customerServiceIF.getCustomerByFirstName(firstName);
  }
}
