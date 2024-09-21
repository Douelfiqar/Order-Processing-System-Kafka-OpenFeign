package org.example.notificationservice.entitie;

import jakarta.persistence.*;
import lombok.*;
import org.example.notificationservice.enums.NotificationType;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int notificationId;
    private int customerId;
    private String message;  // The content of the notification message
    private NotificationType notificationType;  // Enum to differentiate between notification types (e.g., Order Placed, Payment Completed)
    private LocalDateTime sentAt;  // Timestamp when the notification was sent
    private boolean isSent;  // Boolean to track if the notification was successfully sent
}
