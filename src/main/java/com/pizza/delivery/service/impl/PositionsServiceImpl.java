package com.pizza.delivery.service.impl;


import com.pizza.delivery.dto.PositionsDto;
import com.pizza.delivery.model.Positions;
import com.pizza.delivery.repository.PositionsRepository;
import com.pizza.delivery.service.PositionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.pizza.delivery.mappers.PositionsMappers.*;

@Service
public class PositionsServiceImpl implements PositionsService {

    private PositionsRepository positionsRepository;

    @Autowired
    public PositionsServiceImpl(PositionsRepository positionsRepository) {
        this.positionsRepository = positionsRepository;
    }

    @Override
    public List<PositionsDto> findAllPositions() {
        List<Positions> positions = positionsRepository.findAll();
        return positions.stream().map((position) -> mapToPositionsDto(position)).collect(Collectors.toList());
    }

    @Override
    public void savePositions(Positions positions) {
        positionsRepository.save(positions);
    }
}
