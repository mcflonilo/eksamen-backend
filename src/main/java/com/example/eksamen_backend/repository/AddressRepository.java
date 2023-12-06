package com.example.eksamen_backend.repository;

import com.example.eksamen_backend.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
