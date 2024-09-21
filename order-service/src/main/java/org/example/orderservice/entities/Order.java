package org.example.orderservice.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.example.orderservice.model.Customer;

import java.util.List;

@Entity
@Table(name = "`order`")
@AllArgsConstructor @NoArgsConstructor @Builder @Getter @Setter
public class Order {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int orderId;
    private int customerId;
    @Transient
    private Customer customer;
    private double totalAmount;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Item> orderItems;
}
