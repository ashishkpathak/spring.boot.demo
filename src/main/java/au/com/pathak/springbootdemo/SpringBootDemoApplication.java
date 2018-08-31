package au.com.pathak.springbootdemo;

import au.com.pathak.springbootdemo.jpa.CustomerRepository;
import au.com.pathak.springbootdemo.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.context.annotation.Bean;

/**

 *
 */
@SpringBootApplication
@EnableCircuitBreaker
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
  public CommandLineRunner initializeCustomerDatabase(CustomerRepository repository, ApplicationArguments arguments) {


    LOG.debug("Arguments: ", arguments);
    return args
        ->{
      repository.save(new Customer("Homer", "Simpson"));
      repository.save(new Customer("Bart", "Simpson"));
      repository.save(new Customer("Appu", "Nahasapeemapetilon"));
      repository.save(new Customer("Monty", "Burns"));
      repository.save(new Customer("Lisa", "Simpson"));
      repository.save(new Customer("Ned", "Flanders"));
    };


  }




}
