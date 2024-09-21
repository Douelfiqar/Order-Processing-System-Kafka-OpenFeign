package org.example.paymentservice.entitie;

import com.sun.jdi.PrimitiveValue;
import jakarta.persistence.*;
import lombok.*;
import org.example.paymentservice.enums.PaymentMethod;
import org.example.paymentservice.enums.PaymentStatus;
import org.example.paymentservice.model.Order;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int paymentId;

    private int orderId;
    @Transient
    private Order order;

    private double amount;  // Total payment amount

    private String paymentMethod;  // e.g., 'Credit Card', 'PayPal', etc.

    private String status;  // Enum to track payment status

    //private LocalDateTime paymentDate;  // Date and time when the payment was made
}

