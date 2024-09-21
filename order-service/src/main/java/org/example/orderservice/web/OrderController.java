package org.example.orderservice.web;

import org.example.orderservice.client.CustomerRestClient;
import org.example.orderservice.entities.Order;
import org.example.orderservice.model.Customer;
import org.example.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private CustomerRestClient customerRestClient;

    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        return orderService.createOrder(order);
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable int id) {
        Order order = orderService.getOrderById(id);
        Customer customer = customerRestClient.findCustomerById(order.getCustomerId());

        order.setCustomer(customer);
        return order;
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @PutMapping("/{id}")
    public Order updateOrder(@PathVariable int id, @RequestBody Order orderDetails) {
        return orderService.updateOrder(id, orderDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable int id) {
        orderService.deleteOrder(id);
    }
}
