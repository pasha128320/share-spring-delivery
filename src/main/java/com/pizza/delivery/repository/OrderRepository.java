package com.pizza.delivery.repository;

import com.pizza.delivery.model.Order;
import com.pizza.delivery.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findByUserId(Long id);
    List<Order> findAllByUserId(Long id);
}
