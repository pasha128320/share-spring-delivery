package com.pizza.delivery.dto;


import com.pizza.delivery.model.Address;
import com.pizza.delivery.model.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class RegistrationDto {


    private Long id;

//    @Length(min = 8, max = 15)
//    private String username;

//    @Pattern(regexp = "\\+7\\d{10}")
    @NotEmpty
    private String phoneNumber;

    @Length(min=8)
    @NotEmpty
    private String password;

    @Email(message = "Введите корректно свой")
    @NotEmpty
    private String email;

    @Pattern(regexp= "^[А-Яа-яЁё]+$",message = "Имя может состоять только из буквы кириллицы")
    @NotEmpty(message = "firstname may not be empty")
    private String firstName;

    @Pattern(regexp= "^[А-Яа-яЁё]+$",message = "Имя может состоять только из буквы кириллицы")
    @NotEmpty
    private String lastName;

    private List<Role> roles = new ArrayList<>();

    private List<Address> address;


}
