package com.example.eksamen_backend.Machine;

import com.example.eksamen_backend.model.Machine;
import com.example.eksamen_backend.service.MachineService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MachineServiceIntegrationTest {
    @Autowired
    private MachineService machineService;

    @Test
    @Transactional
    void shouldAddAndDeleteMachine() {
        var Machine = new Machine("test maskin");
        machineService.addMachine(Machine);
        var machines = machineService.getMachinesPageable(0);
        System.out.println(machines.size());
        int size = machines.size();
        System.out.println(machines.get(size-1).getMachine_name());
        assert machines.get(size-1).getMachine_name() == "test maskin";
        machineService.deleteMachine(machines.get(size-1).getMachine_id());
        machines = machineService.getMachinesPageable(0);
        assert machines.size() == size-1;
    }
    @Test
    @Transactional
    void shouldUpdateAndGetById() {
        var Machine = new Machine("test maskin");
        var machine = machineService.addMachine(Machine);
        machine.setMachine_name("oppdatert test maskin");
        machineService.updateMachine(machine);
        assert machineService.getMachineById(machine.getMachine_id()).getMachine_name() == "oppdatert test maskin";
    }

}
