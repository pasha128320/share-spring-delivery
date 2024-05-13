package com.pizza.delivery.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class PositionsDto {

    private Long id;

    private String name;
    private String description;
    private String photoUrl;
    private double price;

}
