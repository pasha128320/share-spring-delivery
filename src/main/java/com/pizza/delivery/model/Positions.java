package com.pizza.delivery.model;


import lombok.*;

import jakarta.persistence.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Setter
@Entity(name="positions")
public class Positions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String photoUrl;
    private int price;

}
