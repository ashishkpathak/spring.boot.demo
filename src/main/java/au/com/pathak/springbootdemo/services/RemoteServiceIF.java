package au.com.pathak.springbootdemo.services;

import java.util.List;

import au.com.pathak.springbootdemo.model.Customer;

public interface RemoteServiceIF {

  List<Customer> getRemoteCustomerByFirstName(final String firstName);

  List<Customer> getRemoteCustomerByLastName(final String lastName);
}
