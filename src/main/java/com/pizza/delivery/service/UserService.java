package com.pizza.delivery.service;

import com.pizza.delivery.dto.RegistrationDto;
import com.pizza.delivery.dto.UserDto;
import com.pizza.delivery.model.UserEntity;

public interface UserService {
    void saveUser(RegistrationDto registrationDto);

    UserEntity findUserByEmail(String email);

    UserEntity findUserByPhoneNumber(String phoneNumber);
}
