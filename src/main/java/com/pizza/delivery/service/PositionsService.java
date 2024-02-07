package com.pizza.delivery.service;

import com.pizza.delivery.dto.PositionsDto;
import com.pizza.delivery.model.Positions;

import java.util.List;

public interface PositionsService {

    List<PositionsDto> findAllPositions();
    void savePositions(Positions positions);
}
