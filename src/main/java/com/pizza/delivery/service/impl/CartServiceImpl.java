package com.pizza.delivery.service.impl;

import com.pizza.delivery.model.Cart;
import com.pizza.delivery.model.Positions;
import com.pizza.delivery.repository.CartRepository;
import com.pizza.delivery.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CartRepository cartRepository;

    @Override
    public Cart findCartByUserEmail(String email) {
        return cartRepository.findByEmail(email);
    }

    @Override
    public void addPositionToCart(String email, Positions position) {
        Cart cart = cartRepository.findByEmail(email);
        List<Positions> positionsList = cart.getPositionsList();
        positionsList.add(position);

        cartRepository.save(cart);
    }
}
