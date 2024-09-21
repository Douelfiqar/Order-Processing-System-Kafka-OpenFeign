package org.example.orderservice.model;

import lombok.*;

@Builder @Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Customer {
    private int customerId;
    private String nom;
    private String lastName;
    private String email;
}
