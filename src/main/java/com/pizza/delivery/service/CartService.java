package com.pizza.delivery.service;


import com.pizza.delivery.model.Cart;
import com.pizza.delivery.model.Positions;

public interface CartService {

    Cart findCartByUserEmail(String email);
    void addPositionToCart(String email, Positions position);
}
