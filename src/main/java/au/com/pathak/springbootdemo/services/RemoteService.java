package au.com.pathak.springbootdemo.services;

import java.rmi.Remote;
import java.util.Arrays;
import java.util.List;

import au.com.pathak.springbootdemo.model.Customer;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RemoteService implements RemoteServiceIF {


  @Autowired
  private RestTemplate template;

  @Autowired
  private CustomerServiceIF customerServiceIF;


  @HystrixCommand(fallbackMethod = "getLocalCustomer")
  public List<Customer> getRemoteCustomer(final String firstName) {

    return Arrays.asList(new Customer());
  }


  public List<Customer>  getLocalCustomer(final String firstName) {
    return customerServiceIF.getCustomerByFirstName(firstName);
  }
}
