package au.com.pathak.springbootdemo.controller;

import java.util.Arrays;
import java.util.List;

import au.com.pathak.springbootdemo.model.Customer;
import au.com.pathak.springbootdemo.services.CustomerServiceIF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

  @Autowired
  private CustomerServiceIF service;

  @RequestMapping("/firstname/{firstName}")
  public List<Customer> getCustomersViaFirstName(final @PathVariable String firstName) {

    return service.getCustomerByFirstName(firstName);
  }

  @RequestMapping("/lastname/{lastName}")
  public List<Customer> getCustomersViaLastName(final @PathVariable String lastName) {

    return service.getCustomerByLastName(lastName);
  }
}
