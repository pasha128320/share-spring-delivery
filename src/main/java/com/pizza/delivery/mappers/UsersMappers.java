package com.pizza.delivery.mappers;


import com.pizza.delivery.dto.UserDto;
import com.pizza.delivery.model.UserEntity;

public class UsersMappers {


    static public UserDto mapToUserDto(UserEntity user){
        return UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
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
                .build();
    }
}
