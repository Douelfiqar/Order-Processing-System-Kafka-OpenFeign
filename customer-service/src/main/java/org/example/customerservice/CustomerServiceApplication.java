package org.example.customerservice;

import org.example.customerservice.entitie.Customer;
import org.example.customerservice.service.CustomerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }
    @Bean
    CommandLineRunner insertMockData(CustomerService customerService) {
        return args -> {
            // Creating mock customers
            Customer customer1 = Customer.builder()
                    .nom("John")
                    .lastName("Doe")
                    .email("john.doe@example.com")
                    .build();

            Customer customer2 = Customer.builder()
                    .nom("Jane")
                    .lastName("Smith")
                    .email("jane.smith@example.com")
                    .build();

            Customer customer3 = Customer.builder()
                    .nom("Alice")
                    .lastName("Johnson")
                    .email("alice.johnson@example.com")
                    .build();

            // Inserting customers into the database
            customerService.createCustomer(customer1);
            customerService.createCustomer(customer2);
            customerService.createCustomer(customer3);

            System.out.println("Mock customers inserted!");
        };
    }
}
