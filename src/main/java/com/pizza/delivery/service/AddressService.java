package com.pizza.delivery.service;

import com.pizza.delivery.dto.AddressDto;
import com.pizza.delivery.dto.UserDto;
import com.pizza.delivery.model.Address;
import com.pizza.delivery.model.UserEntity;

import java.util.List;

public interface AddressService {

    List<AddressDto> findAllAdressesByUserId(Long id);

    void saveAddress(AddressDto dto, UserEntity user);
    Address findAddressById(Long id);

    void updateAddress(AddressDto dto);

}
