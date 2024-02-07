package com.pizza.delivery.repository;

import com.pizza.delivery.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
    UserEntity findByPhoneNumber(String phoneNumber);
    UserEntity findByEmail(String email);
    UserEntity findFirstByEmail(String email);
}
