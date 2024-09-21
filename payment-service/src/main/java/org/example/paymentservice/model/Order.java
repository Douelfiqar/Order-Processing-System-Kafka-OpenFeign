package org.example.paymentservice.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Order {
    private int orderId;
    private int customerId;
    private Customer customer;
    private double totalAmount;
    private List<Item> orderItems;
}