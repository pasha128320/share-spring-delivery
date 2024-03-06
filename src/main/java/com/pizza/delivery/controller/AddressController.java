package com.pizza.delivery.controller;


import com.pizza.delivery.model.Address;
import com.pizza.delivery.repository.AddressRepository;
import com.pizza.delivery.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
public class AddressController {

    @Autowired
    UserServiceImpl userService;
    @Autowired
    AddressRepository addressRepository;

    @PostMapping("/address")
    public void addAddress(@RequestBody Address address){
        addressRepository.save(address);
    }

    @GetMapping("/address/get")
    public List<Address> getAllAdress(){
        return addressRepository.findAll();
    }
}
