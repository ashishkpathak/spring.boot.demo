package au.com.pathak.springbootdemo;

import au.com.pathak.springbootdemo.jpa.CustomerRepository;
import au.com.pathak.springbootdemo.jpa.DeviceRepository;
import au.com.pathak.springbootdemo.model.Customer;
import au.com.pathak.springbootdemo.model.Device;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**

 *
 */
@SpringBootApplication
@EnableCircuitBreaker
@EnableJpaAuditing
public class SpringBootDemoApplication {

  private static final Logger LOG = LoggerFactory.getLogger(SpringBootDemoApplication.class);

  public static void main(String[] args) {
    SpringApplication.run(SpringBootDemoApplication.class, args);
  }

  /**
   *
   * @param repository
   * @return
   */

  @Bean
  public CommandLineRunner initializeCustomerDatabase(DeviceRepository deviceRepository, CustomerRepository repository, ApplicationArguments arguments) {


    LOG.debug("Arguments: ", arguments);
    return args
        ->{
      Customer customer = new Customer("Homer", "Simpson");
      Device device = new Device();
      device.setName("DummyName");
      customer.getDeviceSet().add(device);
      deviceRepository.save(device);
      repository.save(customer);
      repository.save(new Customer("Bart", "Simpson"));
      repository.save(new Customer("Appu", "Nahasapeemapetilon"));
      repository.save(new Customer("Monty", "Burns"));
      repository.save(new Customer("Lisa", "Simpson"));
      repository.save(new Customer("Ned", "Flanders"));
    };


  }




}
