package au.com.pathak.springbootdemo.controller;

import java.util.List;

import au.com.pathak.springbootdemo.Config;
import au.com.pathak.springbootdemo.model.Customer;
import au.com.pathak.springbootdemo.services.CustomerServiceIF;
import au.com.pathak.springbootdemo.services.RemoteServiceIF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

  @Autowired
  private CustomerServiceIF customerService;


  @Autowired
//  @Lazy
  private Config.MyBean foo;


  @Autowired
  private RemoteServiceIF remoteService;


  @RequestMapping("/scope")
  public String getScope() {
    return foo.print();

  }
  @RequestMapping("/firstname/{firstName}")
  public List<Customer> getCustomersViaFirstName(final @PathVariable String firstName) {

    return customerService.getCustomerByFirstName(firstName);
  }

  @RequestMapping("/lastname/{lastName}")
  public List<Customer> getCustomersViaLastName(final @PathVariable String lastName) {

    return customerService.getCustomerByLastName(lastName);
  }

  @RequestMapping("/remote/firstname/{firstName}")
  public List<Customer> getRemoteCustomersViaFirstName(final @PathVariable String firstName) {

    return remoteService.getRemoteCustomerByFirstName(firstName);
  }

  @RequestMapping("/remote/lastname/{lastName}")
  public List<Customer> getRemoteCustomersViaLastName(final @PathVariable String lastName) {

    return remoteService.getRemoteCustomerByLastName(lastName);
  }


}
