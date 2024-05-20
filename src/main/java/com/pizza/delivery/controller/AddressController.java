package com.pizza.delivery.controller;


import com.pizza.delivery.dto.AddressDto;
import com.pizza.delivery.dto.UserDto;
import com.pizza.delivery.model.Address;
import com.pizza.delivery.model.UserEntity;
import com.pizza.delivery.repository.AddressRepository;
import com.pizza.delivery.security.SecurityUtil;
import com.pizza.delivery.service.impl.AddressServiceImpl;
import com.pizza.delivery.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static com.pizza.delivery.mappers.UsersMappers.*;
import static com.pizza.delivery.mappers.AddressMappers.*;

@Controller
public class AddressController {

    @Autowired
    UserServiceImpl userService;
    @Autowired
    AddressServiceImpl addressService;
    @Autowired
    AddressRepository addressRepository;

    @GetMapping("/address")
    public String getAddAddressPage(Model model){
        Address address = new Address();

        model.addAttribute("address",address);

        return "address-add";
    }

    @PostMapping("/address")
    public String saveAddress(@ModelAttribute("address")AddressDto dto, Model model){

        String userEmail = SecurityUtil.getSessionUser();
        UserEntity user = userService.findUserByEmail(userEmail);
        addressService.saveAddress(dto, user);


        return "redirect:/profile?address";


    }

    @GetMapping("/address/update/{id}")
    public String updateAddress(Model model, @PathVariable(name="id") Long id){
        Address address = addressService.findAddressById(id);
        model.addAttribute("address", address);

        return "address-update";
    }

    @PostMapping("/address/update/{id}")
    public String postUpdatedAddress(@ModelAttribute(name="address") AddressDto dto, @PathVariable(name="id")Long id){
        String email = SecurityUtil.getSessionUser();
        UserEntity user = userService.findUserByEmail(email);

        addressService.updateAddress(dto);

        return "redirect:/profile?updated";
    }

    @GetMapping("/address/delete/{id}")
    public String deleteAddressById(@PathVariable(name="id")Long id){

        addressRepository.deleteById(id);

        return "redirect:/profile?deleted";
    }



}
