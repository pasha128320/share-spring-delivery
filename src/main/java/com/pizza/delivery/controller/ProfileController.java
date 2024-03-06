package com.pizza.delivery.controller;


import com.pizza.delivery.dto.AddressDto;
import com.pizza.delivery.dto.UserDto;
import com.pizza.delivery.model.UserEntity;
import com.pizza.delivery.security.SecurityUtil;
import com.pizza.delivery.service.impl.AddressServiceImpl;
import com.pizza.delivery.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import static com.pizza.delivery.mappers.UsersMappers.*;

import java.util.List;

@Controller
public class ProfileController {

    @Autowired
    AddressServiceImpl addressService;
    @Autowired
    UserServiceImpl userService;

    @GetMapping("/profile")
    public String getProfilePage(Model model){
        String email = SecurityUtil.getSessionUser();
        UserEntity user = userService.findUserByEmail(email);
        UserDto dto = mapToUserProfileDto(user);
        model.addAttribute("user", user);
        return "profile";
    }

    @GetMapping("/profile/adresses")
    public String getProfilePageWithAdressesList(Model model){
        String userEmail = SecurityUtil.getSessionUser();
        List<AddressDto> adresses = addressService.findAllAdressesByUserEmail(userEmail);
        model.addAttribute("adresses", adresses);

        return "profile-adresses";
    }


}
