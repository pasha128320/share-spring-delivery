package com.pizza.delivery.repository;

import com.pizza.delivery.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.keyvalue.repository.KeyValueRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends KeyValueRepository<Cart, String> {
    Cart findByEmail (String email);

}
