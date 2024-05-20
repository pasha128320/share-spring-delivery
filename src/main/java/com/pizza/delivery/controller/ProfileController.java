package com.pizza.delivery.controller;


import com.pizza.delivery.dto.AddressDto;
import com.pizza.delivery.dto.UserDto;
import com.pizza.delivery.model.Address;
import com.pizza.delivery.model.Order;
import com.pizza.delivery.model.UserEntity;
import com.pizza.delivery.repository.OrderRepository;
import com.pizza.delivery.security.SecurityUtil;
import com.pizza.delivery.service.impl.AddressServiceImpl;
import com.pizza.delivery.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

import static com.pizza.delivery.mappers.UsersMappers.*;

@Controller
public class ProfileController {

    @Autowired
    AddressServiceImpl addressService;
    @Autowired
    UserServiceImpl userService;
    @Autowired
    OrderRepository orderRepository;

    @GetMapping("/profile")
    public String getProfilePage(Model model){
        String email = SecurityUtil.getSessionUser();

        UserEntity user = userService.findUserByEmail(email);
        UserDto dto = mapToUserDto(user);
        model.addAttribute("user", dto);

        List<AddressDto> addresses = addressService.findAllAdressesByUserId(user.getId());
        model.addAttribute("address", addresses);
        System.out.println(addresses.size());

        return "profile";
    }

    @GetMapping("/profile/history")
    public String getProfilePageWithAdressesList(Model model){
        String userEmail = SecurityUtil.getSessionUser();
        UserEntity user = userService.findUserByEmail(userEmail);

        List<Order> orderList = orderRepository.findAllByUserId(user.getId());

        model.addAttribute("orders", orderList);

        return "profile-history";
    }

    @PostMapping("/profile")
    public String updateUserData(Model model, @ModelAttribute(name = "user") UserDto user, BindingResult result){
        UserEntity userExistingPhoneNumber = userService.findUserByPhoneNumber(user.getPhoneNumber());

        if(userExistingPhoneNumber != null && !userExistingPhoneNumber.getEmail().equals(user.getEmail())) {

            result.rejectValue("phoneNumber", "Данный номер телефона уже занят другим пользователем, возможно Вами");
            model.addAttribute("user",user);
            return "redirect:/profile?error";
        }


        userService.updateUser(user);
        return "redirect:/profile?success";
    }




}
