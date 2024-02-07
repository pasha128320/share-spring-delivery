package com.pizza.delivery.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
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
            joinColumns = {@JoinColumn(name="email_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name="role_id", referencedColumnName = "id")}
    )
    private List<Role> roles = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY)
    private List<Address> address;

}
