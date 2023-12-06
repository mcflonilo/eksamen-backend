package com.example.eksamen_backend.controller;

import com.example.eksamen_backend.model.Machine;
import com.example.eksamen_backend.service.MachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/machine")
public class MachineController {
    private final MachineService machineService;

    @Autowired
    public MachineController(MachineService machineService) {
        this.machineService = machineService;
    }

    @GetMapping("/page/{page}")
    public List<Machine> getMachinesPageable(@PathVariable int page) {
        return machineService.getMachinesPageable(page);
    }
    @GetMapping("/{id}")
    public Machine getMachineById(@PathVariable Long id) {
        return machineService.getMachineById(id);
    }
    @PostMapping
    public Machine addMachine(@RequestBody Machine machine) {
        return machineService.addMachine(machine);
    }
    @DeleteMapping("/{id}")
    public void deleteMachine(@PathVariable Long id) {
        machineService.deleteMachine(id);
    }
    @PutMapping
    public Machine updateMachine(@RequestBody Machine machine) {
        return machineService.updateMachine(machine);
    }
}
