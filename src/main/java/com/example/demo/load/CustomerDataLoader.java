package com.example.demo.load;

import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@Slf4j
public class CustomerDataLoader implements CommandLineRunner {

    private final CustomerRepository customerRepository;

    public CustomerDataLoader(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (customerRepository.count() == 0) {
            log.info("Loading initial customer data...");
            List<Customer> customers = List.of(
                Customer.builder().firstName("Alice").lastName("Smith").email("alice.smith@example.com").build(),
                Customer.builder().firstName("Bob").lastName("Jones").email("bob.jones@example.com").build(),
                Customer.builder().firstName("Charlie").lastName("Brown").email("charlie.brown@example.com").build(),
                Customer.builder().firstName("David").lastName("Miller").email("david.miller@example.com").build(),
                Customer.builder().firstName("Emma").lastName("Davis").email("emma.davis@example.com").build(),
                Customer.builder().firstName("Frank").lastName("Garcia").email("frank.garcia@example.com").build(),
                Customer.builder().firstName("Grace").lastName("Rodriguez").email("grace.rodriguez@example.com").build(),
                Customer.builder().firstName("Henry").lastName("Wilson").email("henry.wilson@example.com").build(),
                Customer.builder().firstName("Isabella").lastName("Martinez").email("isabella.martinez@example.com").build(),
                Customer.builder().firstName("Jack").lastName("Anderson").email("jack.anderson@example.com").build(),
                Customer.builder().firstName("Kate").lastName("Taylor").email("kate.taylor@example.com").build(),
                Customer.builder().firstName("Luke").lastName("Thomas").email("luke.thomas@example.com").build(),
                Customer.builder().firstName("Mia").lastName("Moore").email("mia.moore@example.com").build(),
                Customer.builder().firstName("Noah").lastName("Martin").email("noah.martin@example.com").build(),
                Customer.builder().firstName("Olivia").lastName("Jackson").email("olivia.jackson@example.com").build()
            );
            customerRepository.saveAll(customers);
            log.info("Successfully loaded {} customers into the H2 database.", customerRepository.count());
        }
    }
}
