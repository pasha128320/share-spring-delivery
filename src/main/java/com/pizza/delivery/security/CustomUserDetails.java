package com.pizza.delivery.security;

import com.pizza.delivery.model.UserEntity;
import com.pizza.delivery.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
public class CustomUserDetails implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity user = userRepository.findFirstByEmail(email);

        if(user == null){
            throw new UsernameNotFoundException("Пользователь не найден");
        }
        return new User(user.getEmail()
                ,user.getPassword()
                ,user.getRoles().stream().map((role) -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList()));
    }


}
