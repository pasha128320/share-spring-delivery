package com.pizza.delivery.dto;


import com.pizza.delivery.model.Address;
import com.pizza.delivery.model.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class UserDto {

    private Long id;

    private String email;
    private String phoneNumber;
    private String firstName;
    private String lastName;

    private List<Address> address;

    private List<Role> roles;
}
