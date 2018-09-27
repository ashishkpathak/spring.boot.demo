package au.com.pathak.springbootdemo.jpa;

import au.com.pathak.springbootdemo.model.Customer;
import au.com.pathak.springbootdemo.model.Device;
import org.springframework.data.repository.CrudRepository;

public interface DeviceRepository extends CrudRepository<Device, Long>  {


  public Device findByName(final String name);



}
