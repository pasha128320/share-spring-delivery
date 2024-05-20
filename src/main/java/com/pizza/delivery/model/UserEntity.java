package com.pizza.delivery.model;


import lombok.*;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String phoneNumber;
    private String password;

    private String email;

    private String firstName;
    private String lastName;



    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name= "users_roles",
            joinColumns = {@JoinColumn(name="user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name="role_id", referencedColumnName = "id")}
    )
    private List<Role> roles;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Address> address;

}
