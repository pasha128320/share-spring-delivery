package com.pizza.delivery.repository;

import com.pizza.delivery.dto.AddressDto;
import com.pizza.delivery.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address,Long> {

    List<Address> findAllByUserEmail(String email);
}
