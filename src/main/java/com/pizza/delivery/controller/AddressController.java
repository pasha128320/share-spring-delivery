package com.pizza.delivery.controller;


import com.pizza.delivery.dto.AddressDto;
import com.pizza.delivery.dto.UserDto;
import com.pizza.delivery.model.Address;
import com.pizza.delivery.model.UserEntity;
import com.pizza.delivery.security.SecurityUtil;
import com.pizza.delivery.service.impl.AddressServiceImpl;
import com.pizza.delivery.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import static com.pizza.delivery.mappers.UsersMappers.*;

@Controller
public class AddressController {

    @Autowired
    UserServiceImpl userService;
    @Autowired
    AddressServiceImpl addressService;

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

}
