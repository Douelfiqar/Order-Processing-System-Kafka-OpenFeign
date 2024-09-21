package org.example.paymentservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Item {
    private int itemId;
    private String itemName;
}
