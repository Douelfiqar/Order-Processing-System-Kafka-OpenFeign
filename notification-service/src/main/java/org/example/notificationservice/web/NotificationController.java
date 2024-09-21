package org.example.notificationservice.web;

import org.example.notificationservice.entitie.Notification;
import org.example.notificationservice.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {
    @Autowired
    private NotificationService notificationService;

    @PostMapping
    public Notification createNotification(@RequestBody Notification notification) {
        return notificationService.createNotification(notification);
    }

    @GetMapping("/{id}")
    public Notification getNotificationById(@PathVariable int id) {
        return notificationService.getNotificationById(id);
    }

    @GetMapping
    public List<Notification> getAllNotifications() {
        return notificationService.getAllNotifications();
    }

    @PutMapping("/{id}")
    public Notification updateNotification(@PathVariable int id, @RequestBody Notification notificationDetails) {
        return notificationService.updateNotification(id, notificationDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteNotification(@PathVariable int id) {
        notificationService.deleteNotification(id);
    }

    @GetMapping("/customer/{customerId}")
    public List<Notification> getNotificationsByCustomerId(@PathVariable int customerId) {
        return notificationService.findByCustomerId(customerId);
    }
}
