package org.example.notificationservice.model;

import jakarta.persistence.Transient;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Payment {
    private int paymentId;
    private int orderId;
    private Order order;
    private double amount;  // Total payment amount
    private String paymentMethod;  // e.g., 'Credit Card', 'PayPal', etc.
    private String status;  // Enum to track payment status
    //private LocalDateTime paymentDate;
}
