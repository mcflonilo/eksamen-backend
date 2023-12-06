package com.example.eksamen_backend.Machine;

import com.example.eksamen_backend.model.Machine;
import com.example.eksamen_backend.repository.MachineRepository;
import com.example.eksamen_backend.service.MachineService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(properties = {"command.line.runner.enabled=false"})
public class MachineServiceUnitTest {
    @MockBean
    private MachineRepository machineRepo;
    @Autowired
    private MachineService machineService;

    @Test
    void shouldGetMachineById() {
        var machine = new Machine("unit testMaskin");
        when(machineRepo.findById(1L)).thenReturn(Optional.of(machine));
        var foundMachine = machineService.getMachineById(1L);
        assert foundMachine.getMachine_name() == "unit testMaskin";
    }

    //https://stackoverflow.com/questions/55448188/spring-boot-pagination-mockito-repository-findallpageable-returns-null
    @Test
    void shouldGetMachinesPageable() {
        var machine = new Machine("unit testMaskin");

        Page<Machine> machines = new PageImpl<>(List.of(machine));
        when(machineRepo.findAll(org.mockito.ArgumentMatchers.isA(Pageable.class))).thenReturn(machines);
        var foundMachine = machineService.getMachinesPageable(0);
        assert foundMachine.equals( machines.stream().toList());
    }
    @Test
    void shouldAddMachine() {
        var machine = new Machine("unit testMaskin");
        when(machineRepo.save(machine)).thenReturn(machine);
        var savedMachine = machineService.addMachine(machine);
        assert savedMachine.getMachine_name() == "unit testMaskin";
    }
    @Test
    void shouldDeleteMachine() {
        var machine = new Machine("slett meg");
        when(machineRepo.save(machine)).thenReturn(machine);
        machine = machineService.addMachine(machine);
        when(machineRepo.findById(machine.getMachine_id())).thenReturn(Optional.of(machine));
        machineService.deleteMachine(machine.getMachine_id());
        verify(machineRepo).deleteById(machine.getMachine_id());
    }
    @Test
    void shouldUpdateMachine() {
        var machine = new Machine("unit testMaskin");
        when(machineRepo.save(machine)).thenReturn(machine);
        var savedMachine = machineService.addMachine(machine);
        savedMachine.setMachine_name("unit testMaskin2");
        when(machineRepo.save(savedMachine)).thenReturn(savedMachine);
        var updatedMachine = machineService.updateMachine(savedMachine);
        assert updatedMachine.getMachine_name() == "unit testMaskin2";
    }
}
