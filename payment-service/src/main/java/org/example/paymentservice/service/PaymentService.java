package org.example.paymentservice.service;

import jakarta.transaction.Transactional;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.example.paymentservice.client.OrderRestClient;
import org.example.paymentservice.entitie.Payment;
import org.example.paymentservice.enums.PaymentMethod;
import org.example.paymentservice.enums.PaymentStatus;
import org.example.paymentservice.model.Item;
import org.example.paymentservice.model.Order;
import org.example.paymentservice.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.management.Notification;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Transactional
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private OrderRestClient orderRestClient;
    @Autowired
    private KafkaTemplate<String, Payment> kafkaTemplate; // Autowire KafkaTemplate for publishing

    @Bean
    public Function<Order, Payment> paymentFunctionTmp() {
        return order -> {
            // Display the received order information
            System.out.println("********** Order Received **********");
            System.out.println("Order ID: " + order.getOrderId());
            System.out.println("Customer ID: " + order.getCustomerId());
            System.out.println("Total Amount: " + order.getTotalAmount());
            System.out.println("Customer Name: " + order.getCustomer().getNom());
            System.out.println("Items: ");
            for (Item item : order.getOrderItems()) {
                System.out.println("- " + item.getItemName());
            }

            // Create the payment object
            Payment payment = Payment.builder()
                    .paymentMethod("Credit_Card")
                    .amount(order.getTotalAmount() + 20)
                    .order(order)
                    .status("COMPLETED")
                    .build();

            System.out.println("********** Payment Created **********");
            System.out.println("Payment ID: " + payment.getPaymentId());
            System.out.println("Payment Amount: " + payment.getAmount());
            System.out.println("Payment Status: " + payment.getStatus());

            return payment;
        };
    }

    public Consumer<Order> orderConsumer() {
        return order -> {
            System.out.println("********** Order Received in Payment Service **********");
            System.out.println("Order ID: " + order.getOrderId());
            System.out.println("Customer ID: " + order.getCustomerId());
            System.out.println("Total Amount: " + order.getTotalAmount());
            System.out.println("Customer Name: " + order.getCustomer().getNom());
            System.out.println("Items: ");
            for (Item item : order.getOrderItems()) {
                System.out.println("- " + item.getItemName());
            }
            // Add payment logic here
            System.out.println("************************************");
        };
    }

    public Payment createPayment(Payment payment) {
        //payment.setPaymentDate(LocalDateTime.now());  // Set the payment date to current time
        payment.setStatus("PENDING");  // Default status is 'PENDING'
        return paymentRepository.save(payment);
    }

    public Payment getPaymentById(int id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found with id: " + id));
        Order order = orderRestClient.findOrderById(payment.getOrderId());
        payment.setOrder(order);
        return payment;
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public Payment updatePayment(int id, Payment paymentDetails) {
        Payment existingPayment = getPaymentById(id);
        existingPayment.setAmount(paymentDetails.getAmount());
        existingPayment.setPaymentMethod(paymentDetails.getPaymentMethod());
        existingPayment.setStatus(paymentDetails.getStatus());
        //existingPayment.setPaymentDate(paymentDetails.getPaymentDate());
        return paymentRepository.save(existingPayment);
    }

    public void deletePayment(int id) {
        Payment existingPayment = getPaymentById(id);
        paymentRepository.delete(existingPayment);
    }

    public List<Payment> findByOrderId(int orderId) {
        return paymentRepository.findAll().stream()
                .filter(payment -> payment.getOrderId() == orderId)
                .collect(Collectors.toList());
    }
}
