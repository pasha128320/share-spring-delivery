package com.pizza.delivery.repository;

import com.pizza.delivery.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
