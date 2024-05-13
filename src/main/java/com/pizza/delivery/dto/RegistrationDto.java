package com.pizza.delivery.dto;


import com.pizza.delivery.model.Address;
import com.pizza.delivery.model.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
public class RegistrationDto {


    private Long id;

    @NotEmpty
    private String phoneNumber;

    @Length(min=8)
    @NotEmpty
    private String password;

    @Email(message = "Введите корректно свой адрес почты")
    @NotEmpty
    private String email;

    @Pattern(regexp= "^[А-Яа-яЁё]+$",message = "Имя может состоять только из буквы кириллицы")
    @NotEmpty(message = "firstname may not be empty")
    private String firstName;

    @Pattern(regexp= "^[А-Яа-яЁё]+$",message = "Имя может состоять только из буквы кириллицы")
    @NotEmpty
    private String lastName;

    private List<Role> roles = new ArrayList<>();

    private List<Address> address = new ArrayList<>();


}
