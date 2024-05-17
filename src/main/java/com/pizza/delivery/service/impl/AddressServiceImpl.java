package com.pizza.delivery.service.impl;


import com.pizza.delivery.dto.AddressDto;
import com.pizza.delivery.dto.UserDto;
import com.pizza.delivery.model.Address;
import com.pizza.delivery.model.UserEntity;
import com.pizza.delivery.repository.AddressRepository;
import com.pizza.delivery.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static com.pizza.delivery.mappers.AddressMappers.*;
import static com.pizza.delivery.mappers.UsersMappers.*;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressServiceImpl implements AddressService {

    AddressRepository addressRepository;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }


    @Override
    public List<AddressDto> findAllAdressesByUserId(Long id) {
        List<Address> addresses = addressRepository.findAllByUserId(id);
        return addresses.stream().map(elem -> mapToAddressDto(elem)).collect(Collectors.toList());
    }

    @Override
    public void saveAddress(AddressDto dto, UserEntity user) {
        dto.setUser(user);
        addressRepository.save(mapToAddress(dto));
    }

    @Override
    public Address findAddressById(Long id) {
        Address address = addressRepository.findById(id).get();
        return address;
    }


}
