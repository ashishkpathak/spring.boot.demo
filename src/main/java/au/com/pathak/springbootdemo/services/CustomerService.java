package au.com.pathak.springbootdemo.services;

import java.util.List;

import au.com.pathak.springbootdemo.jpa.CustomerRepository;
import au.com.pathak.springbootdemo.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService implements CustomerServiceIF {


  @Autowired
  private CustomerRepository repository;

  @Override
  public List<Customer> getCustomerByFirstName(String firstName) {
    return repository.findByFirstName(firstName);
  }

  @Override
  public List<Customer> getCustomerByLastName(String firstName) {
    return repository.findByLastName(firstName);
  }
}
