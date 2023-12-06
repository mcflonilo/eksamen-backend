package com.example.eksamen_backend.repository;

import com.example.eksamen_backend.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
