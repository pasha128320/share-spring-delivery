package com.pizza.delivery.model;


import jakarta.persistence.*;
import lombok.*;


@NoArgsConstructor
@Builder
@Setter
@Getter
@Entity(name="address")
public class Address {

    public Address(Long id, String name, String city, String street, String commentToDelivery, int houseNumber, int apartNumber, int floor, UserEntity user) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.street = street;
        this.commentToDelivery = commentToDelivery;
        this.houseNumber = houseNumber;
        this.apartNumber = apartNumber;
        this.floor = floor;
        this.user = user;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String city;
    private String street;
    private String commentToDelivery;
    private int houseNumber;
    private int apartNumber;
    private int floor;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name="user_id", referencedColumnName = "id")
    private UserEntity user;


}
