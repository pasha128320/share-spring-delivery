package com.pizza.delivery.model;


import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
@Entity(name="address")
public class Address {

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

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="user_email", referencedColumnName = "email")
    private UserEntity user;
}
