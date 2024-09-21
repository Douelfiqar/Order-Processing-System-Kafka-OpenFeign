package org.example.paymentservice.repository;

import org.example.paymentservice.entitie.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
}
