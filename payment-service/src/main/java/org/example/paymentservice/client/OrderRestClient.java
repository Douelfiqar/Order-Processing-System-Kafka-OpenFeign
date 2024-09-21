package org.example.paymentservice.client;

import org.example.paymentservice.model.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ORDER-SERVICE")
public interface OrderRestClient {
    @GetMapping("/api/orders/{id}")
    public Order findOrderById(@PathVariable int id);
}
