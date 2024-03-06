package com.pizza.delivery.mappers;


import com.pizza.delivery.dto.RegistrationDto;
import com.pizza.delivery.dto.UserDto;
import com.pizza.delivery.model.UserEntity;

public class UsersMappers {


    static public UserDto mapToUserProfileDto(UserEntity user){
        return UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .address(user.getAddress())
                .roles(user.getRoles())
                .build();
    }

    static public UserEntity mapToUserEntity(UserDto dto){
        return UserEntity.builder()
                .id(dto.getId())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .phoneNumber(dto.getPhoneNumber())
                .roles(dto.getRoles())
                .email(dto.getEmail())
                .address(dto.getAddress())
                .build();
    }
}
