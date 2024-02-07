package com.pizza.delivery.mappers;


import com.pizza.delivery.dto.PositionsDto;
import com.pizza.delivery.model.Positions;

public class PositionsMappers {

    public static PositionsDto mapToPositionsDto(Positions positions){
        return PositionsDto.builder()
                .id(positions.getId())
                .name(positions.getName())
                .price(positions.getPrice())
                .description(positions.getDescription())
                .photoUrl(positions.getPhotoUrl())
                .build();
    }

    public static Positions mapToPositions(PositionsDto positionsDto){
        return Positions.builder()
                .id(positionsDto.getId())
                .name(positionsDto.getName())
                .price(positionsDto.getPrice())
                .description(positionsDto.getDescription())
                .photoUrl(positionsDto.getPhotoUrl())
                .build();
    }
}
