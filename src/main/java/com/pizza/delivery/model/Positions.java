package com.pizza.delivery.model;


import lombok.*;

import jakarta.persistence.*;


@NoArgsConstructor
//@AllArgsConstructor
@Getter
@Builder
@Setter
@Entity(name="positions") // Аннотация, которая обозначает данный класс как сущность
public class Positions {

    public Positions(Long id, String name, String description, String photoUrl, double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.photoUrl = photoUrl;
        this.price = price;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String photoUrl;
    private double price;

}
