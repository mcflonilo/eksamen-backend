package com.example.eksamen_backend.service;

import com.example.eksamen_backend.model.Machine;
import com.example.eksamen_backend.model.Order;
import com.example.eksamen_backend.repository.MachineRepository;
import com.example.eksamen_backend.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MachineService {
    private final MachineRepository machineRepository;

    @Autowired
    public MachineService(MachineRepository machineRepository) {
        this.machineRepository = machineRepository;
    }
    //get one by id
    public Machine getMachineById(Long id) {
        return machineRepository.findById(id).orElse(null);
    }
    //get all with pagination
    public List<Machine> getMachinesPageable(int pageNumber) {
        return machineRepository.findAll(PageRequest.of(pageNumber, 10)).stream().toList();
    }
    //create one
    public Machine addMachine(Machine machine) {
        return machineRepository.save(machine);
    }
    //delete one
    public void deleteMachine(Long id) {
        machineRepository.deleteById(id);
    }
    //update one
    public Machine updateMachine(Machine machine) {
        return machineRepository.save(machine);
    }


}
