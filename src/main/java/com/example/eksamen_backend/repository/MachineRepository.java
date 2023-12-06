package com.example.eksamen_backend.repository;

import com.example.eksamen_backend.model.Machine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MachineRepository extends JpaRepository<Machine, Long> {
}
