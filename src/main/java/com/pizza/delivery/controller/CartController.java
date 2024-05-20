package com.pizza.delivery.controller;


import com.pizza.delivery.dto.AddressDto;
import com.pizza.delivery.dto.PositionsDto;
import com.pizza.delivery.dto.UserDto;
import com.pizza.delivery.model.*;
import com.pizza.delivery.repository.CartRepository;
import com.pizza.delivery.repository.OrderRepository;
import com.pizza.delivery.security.SecurityUtil;
import com.pizza.delivery.service.CartService;
import com.pizza.delivery.service.UserService;
import com.pizza.delivery.service.impl.AddressServiceImpl;
import com.pizza.delivery.service.impl.CartServiceImpl;
import static com.pizza.delivery.mappers.AddressMappers.*;
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
    @Autowired
    OrderRepository orderRepository;


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
    public String addPositionToCart(@ModelAttribute(name="elem") Positions position){
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

    @GetMapping("/cart/{positionId}/delete")
    public String deletePositionFromCart(@PathVariable(name="positionId")Long positionId){
        String email = SecurityUtil.getSessionUser();
        Cart cart = cartService.findCartByUserEmail(email);
        List<Positions> positionsList = cart.getPositionsList();
        double cartSummary = cart.getSummary();

        for(var elem : positionsList){
            if(elem.getId().equals(positionId)){
                cartSummary -= elem.getPrice();
                positionsList.remove(elem);
                break;
            }
        }

        cart.setSummary(cartSummary);
        cart.setPositionsList(positionsList);
        cartRepository.save(cart);

        return"redirect:/cart?deleted";
    }


    @PostMapping("/order")
    public String createNewOrder(@ModelAttribute(name="address")Long id){
        String email = SecurityUtil.getSessionUser();
        UserEntity user = userService.findUserByEmail(email);
        Address address = addressService.findAddressById(id);
        Cart cart = cartService.findCartByUserEmail(email);

        Order order = new Order();
        order.setSummary(cart.getSummary());
        order.setPositionsList(cart.getPositionsList());
        order.setUser(user);
        order.setAddress(address);

        orderRepository.save(order);
        cartRepository.deleteById(cart.getEmail());



        return "redirect:/?successOrder";
    }

}
