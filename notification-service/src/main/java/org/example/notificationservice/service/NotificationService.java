package org.example.notificationservice.service;

import jakarta.transaction.Transactional;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.example.notificationservice.entitie.Notification;

import org.example.notificationservice.model.Item;
import org.example.notificationservice.model.Order;
import org.example.notificationservice.model.Payment;
import org.example.notificationservice.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Service
@Transactional
public class NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;
    //@Autowired
    //private CustomerRestClient customerRestClient;
    @Bean
    public Consumer<Order> orderConsumer(){
        return order -> {
            System.out.println("********** Order Received **********");
            System.out.println("Order received");
            System.out.println("Order ID: " + order.getOrderId());
            System.out.println("Customer ID: " + order.getCustomerId());
            System.out.println("Total Amount: " + order.getTotalAmount());
            System.out.println("Customer Name: " + order.getCustomer().getNom());
            System.out.println("Items: ");
            for (Item item : order.getOrderItems()) {
                System.out.println("- " + item.getItemName());
            }
            System.out.println("************************************");
        };
    }
    @Bean
    public Consumer<Payment> paymentConsumer() {
        return payment -> {
            System.out.println("********** Payment Received **********");
            System.out.println("Payment ID: " + payment.getPaymentId());
            System.out.println("Payment Amount: " + payment.getAmount());
            System.out.println("Payment Status: " + payment.getStatus());
            System.out.println("Order ID: " + payment.getOrder().getOrderId());
            System.out.println("Customer ID: " + payment.getOrder().getCustomer().getCustomerId());
            System.out.println("************************************");
        };
    }
    public Notification createNotification(Notification notification) {
        notification.setSent(false); // Default to not sent
        notification.setSentAt(LocalDateTime.now()); // Set the current timestamp
        return notificationRepository.save(notification);
    }

    public Notification getNotificationById(int id) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Notification not found with id: " + id));
       // Customer customer = customerRestClient.findCustomerById(id);
        //notification.setCustomer(customer);
        return notification;
    }

    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    public Notification updateNotification(int id, Notification notificationDetails) {
        Notification existingNotification = getNotificationById(id);
        existingNotification.setMessage(notificationDetails.getMessage());
        existingNotification.setNotificationType(notificationDetails.getNotificationType());
        existingNotification.setSentAt(notificationDetails.getSentAt());
        existingNotification.setSent(notificationDetails.isSent());
        return notificationRepository.save(existingNotification);
    }

    public void deleteNotification(int id) {
        Notification existingNotification = getNotificationById(id);
        notificationRepository.delete(existingNotification);
    }

    public List<Notification> findByCustomerId(int customerId) {
        return notificationRepository.findAll().stream()
                .filter(notification -> notification.getCustomerId() == customerId)
                .collect(Collectors.toList());
    }
}
