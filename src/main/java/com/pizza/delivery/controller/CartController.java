package com.pizza.delivery.controller;


import com.pizza.delivery.dto.AddressDto;
import com.pizza.delivery.dto.PositionsDto;
import com.pizza.delivery.dto.UserDto;
import com.pizza.delivery.model.Cart;
import com.pizza.delivery.model.Positions;
import com.pizza.delivery.model.UserEntity;
import com.pizza.delivery.repository.CartRepository;
import com.pizza.delivery.security.SecurityUtil;
import com.pizza.delivery.service.CartService;
import com.pizza.delivery.service.UserService;
import com.pizza.delivery.service.impl.AddressServiceImpl;
import com.pizza.delivery.service.impl.CartServiceImpl;
import com.pizza.delivery.service.impl.PositionsServiceImpl;
import lombok.Getter;
import org.aspectj.weaver.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import static com.pizza.delivery.mappers.PositionsMappers.*;

import java.util.List;

@Controller
public class CartController {

    @Autowired
    CartServiceImpl cartService;
    @Autowired
    CartRepository cartRepository;
    @Autowired
    UserService userService;
    @Autowired
    PositionsServiceImpl positionsService;
    @Autowired
    AddressServiceImpl addressService;


    @GetMapping("/cart")
    public String getCartPage(Model model){
        String email = SecurityUtil.getSessionUser();
        Cart cart = cartService.findCartByUserEmail(email);
        UserEntity user = userService.findUserByEmail(email);
        List<AddressDto> addresses = addressService.findAllAdressesByUserId(user.getId());

        if(cart == null || cart.getPositionsList().isEmpty()){
            return "redirect:/?empty";
        }


        model.addAttribute("addresses", addresses);
        model.addAttribute("positions",cart.getPositionsList());
        model.addAttribute("cart", cart);
        return "cart";
    }

    @PostMapping("/addToCart")
    public String AddPositionToCart(@ModelAttribute(name="elem") Positions position){
        String email = SecurityUtil.getSessionUser();
        Cart cart = cartRepository.findByEmail(email);

        if(cart != null){
            List<Positions> positionsList = cart.getPositionsList();

            cart.setSummary(cart.getSummary() + position.getPrice());

            positionsList.add(position);
            cart.setPositionsList(positionsList);
            cartRepository.save(cart);
        }
        return "redirect:/?ordered";
    }

}
