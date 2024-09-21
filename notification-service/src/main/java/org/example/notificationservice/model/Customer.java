package org.example.notificationservice.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Customer {
    private int customerId;
    private String nom;
    private String lastName;
    private String email;
}
