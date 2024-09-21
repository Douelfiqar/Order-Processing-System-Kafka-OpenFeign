package org.example.orderservice.service;

import jakarta.transaction.Transactional;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.example.orderservice.client.CustomerRestClient;
import org.example.orderservice.entities.Order;
import org.example.orderservice.model.Customer;
import org.example.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.cloud.stream.function.StreamBridge;
import java.util.List;

@Service
@Transactional
public class OrderService{
    private static final String TOPIC = "test";
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CustomerRestClient customerRestClient;
    @Autowired
    private StreamBridge streamBridge;
    public Order createOrder(Order order) {
        Order order1 = orderRepository.save(order);
        Customer customer = customerRestClient.findCustomerById(order.getCustomerId());
        order1.setCustomer(customer);
        streamBridge.send(TOPIC, order1);
        System.out.println("Produced message: " + order1 + " to topic: " + TOPIC);
        return order1;
    }

    public Order getOrderById(int id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + id));
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order updateOrder(int id, Order orderDetails) {
        Order existingOrder = getOrderById(id);
        existingOrder.setCustomerId(orderDetails.getCustomerId());
        existingOrder.setTotalAmount(orderDetails.getTotalAmount());
        existingOrder.setOrderItems(orderDetails.getOrderItems());
        return orderRepository.save(existingOrder);
    }

    public void deleteOrder(int id) {
        Order existingOrder = getOrderById(id);
        orderRepository.delete(existingOrder);
    }
}