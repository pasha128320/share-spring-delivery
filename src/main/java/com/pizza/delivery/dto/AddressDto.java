package com.pizza.delivery.dto;


import com.pizza.delivery.model.UserEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Builder
@Getter
@Setter
public class AddressDto {

    private Long id;

    private String name;
    private String city;
    private String street;
    private String commentToDelivery;
    private int houseNumber;
    private int apartNumber;
    private int floor;

    private UserEntity user;
}
