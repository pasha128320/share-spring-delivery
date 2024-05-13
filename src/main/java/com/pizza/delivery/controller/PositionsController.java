package com.pizza.delivery.controller;


import com.pizza.delivery.dto.PositionsDto;
import com.pizza.delivery.model.Cart;
import com.pizza.delivery.model.Positions;
import com.pizza.delivery.repository.CartRepository;
import com.pizza.delivery.security.SecurityUtil;
import com.pizza.delivery.service.impl.PositionsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import static com.pizza.delivery.mappers.PositionsMappers.mapToPositions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
public class PositionsController {

    PositionsServiceImpl positionsService;
    CartRepository cartRepository;

    @Autowired
    public PositionsController(PositionsServiceImpl positionsService, CartRepository cartRepository) {
        this.positionsService = positionsService;
        this.cartRepository = cartRepository;
    }

    @GetMapping
    public String getHomePage(Model model){
        List<PositionsDto> positions = positionsService.findAllPositions();
        model.addAttribute("positions", positions);

        String email = SecurityUtil.getSessionUser();

        if(email != null && !email.isEmpty()) {
            Cart cart = cartRepository.findByEmail(email);
            if (cart == null) {
                Cart newCart = new Cart();
                newCart.setSummary(0);
                newCart.setEmail(email);
                cartRepository.save(newCart);
            }
        }

        model.addAttribute("title","Главная страница");
        return "home";
    }

    @GetMapping("/add")
    public String addPosition(Model model){
        Positions positions = new Positions();
        model.addAttribute("position",positions);
        return "add-position";
    }

    @PostMapping("/add")
    public String postAddPosition(@ModelAttribute("position") PositionsDto dto, Model model){
        Positions positions = mapToPositions(dto);
        positionsService.savePositions(positions);

        return"redirect:/";
    }




}
