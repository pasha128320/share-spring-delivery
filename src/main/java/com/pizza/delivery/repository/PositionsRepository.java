package com.pizza.delivery.repository;

import com.pizza.delivery.dto.PositionsDto;
import com.pizza.delivery.model.Positions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionsRepository extends JpaRepository<Positions, Long> {
    Positions findByName(String name);
}
