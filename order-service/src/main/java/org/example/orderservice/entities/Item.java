package org.example.orderservice.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder @Getter
@Setter
public class Item {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int itemId;
    private String itemName;
    @ManyToOne
    @JsonBackReference
    private Order order;
}
