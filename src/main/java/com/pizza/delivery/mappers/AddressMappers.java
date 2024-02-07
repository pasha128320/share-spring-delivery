package com.pizza.delivery.mappers;


import com.pizza.delivery.dto.AddressDto;
import com.pizza.delivery.model.Address;

public class AddressMappers {

    public static AddressDto mapToAddressDto(Address address){
        return AddressDto.builder()
                .id(address.getId())
                .user(address.getUser())
                .name(address.getName())
                .apartNumber(address.getApartNumber())
                .city(address.getCity())
                .floor(address.getFloor())
                .commentToDelivery(address.getCommentToDelivery())
                .houseNumber(address.getHouseNumber())
                .street(address.getStreet())
                .build();
    }

    public static Address mapToAddress(AddressDto address){
        return Address.builder()
                .id(address.getId())
                .user(address.getUser())
                .name(address.getName())
                .apartNumber(address.getApartNumber())
                .city(address.getCity())
                .floor(address.getFloor())
                .commentToDelivery(address.getCommentToDelivery())
                .houseNumber(address.getHouseNumber())
                .street(address.getStreet())
                .build();
    }
}
