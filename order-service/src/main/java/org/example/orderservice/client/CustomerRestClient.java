package org.example.orderservice.client;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.example.orderservice.model.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="CUSTOMER-SERVICE")
public interface CustomerRestClient {
    @GetMapping("/api/customers/{id}")
    @CircuitBreaker(name = "customerService", fallbackMethod = "getDefaultCustomer")
    public Customer findCustomerById(@PathVariable int id);

    default Customer getDefaultCustomer(int id, Exception exception){
        return Customer.builder()
                .customerId(id)
                .lastName("Not available")
                .email("Not available")
                .lastName("Not available")
                .build();
    }
}
