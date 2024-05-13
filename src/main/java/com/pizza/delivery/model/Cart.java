package com.pizza.delivery.model;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
@RedisHash("Carts")
public class Cart {

    @Id
    @Indexed
    private String email;

    private List<Positions> positionsList = new ArrayList<>();

    private double summary;


}
