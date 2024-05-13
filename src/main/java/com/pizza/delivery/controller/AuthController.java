package com.pizza.delivery.controller;


import com.pizza.delivery.dto.RegistrationDto;
import com.pizza.delivery.model.UserEntity;
import com.pizza.delivery.service.impl.UserServiceImpl;
import jakarta.validation.Valid;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
public class AuthController {

    UserServiceImpl userService;


    @Autowired
    public AuthController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String getRegisterForm(Model model){
        UserEntity user = new UserEntity();
        model.addAttribute("user",user);
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute(name="user") RegistrationDto user, BindingResult result, Model model){
        UserEntity existingUserEmail = userService.findUserByEmail(user.getEmail());
        if (existingUserEmail != null && existingUserEmail.getEmail() != null && !existingUserEmail.getEmail().isEmpty()){
            result.rejectValue("email", "Данный почтовый адрес уже занят");
        }
        UserEntity existingUserPhoneNumber = userService.findUserByPhoneNumber(user.getPhoneNumber());
        if(existingUserPhoneNumber != null && existingUserPhoneNumber.getPhoneNumber() != null && !existingUserPhoneNumber.getPhoneNumber().isEmpty()) {
            result.rejectValue("phoneNumber", "Данный номер телефона уже занят");
        }

        if(result.hasErrors()){
            model.addAttribute("user",user);
            return "redirect:/register?error";
        }

        userService.saveUser(user);
        return "redirect:/?success";
    }


    @GetMapping("/login")
    public String getLoginPage(Model model){
        return "login";
    }



}
