package com.pizza.delivery.service;

import com.pizza.delivery.dto.AddressDto;

import java.util.List;

public interface AddressService {

    List<AddressDto> findAllAdressesByUserEmail(String email);

    void saveAddress(AddressDto dto);

}
