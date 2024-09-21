package org.example.notificationservice.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
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
