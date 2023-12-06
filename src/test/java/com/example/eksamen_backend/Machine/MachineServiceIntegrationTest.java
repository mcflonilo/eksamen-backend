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
        assert machines.size() == 5;
        System.out.println(machines.get(4).getMachine_name());
        assert machines.get(4).getMachine_name() == "test maskin";
        machineService.deleteMachine(machines.get(4).getMachine_id());
        machines = machineService.getMachinesPageable(0);
        assert machines.size() == 4;
    }
    @Test
    @Transactional
    void shouldUpdateAndGetById() {
        var Machine = new Machine("test maskin");
        machineService.addMachine(Machine);
        var machine = machineService.getMachineById((long) 3);
        machine.setMachine_name("oppdatert test maskin");
        machineService.updateMachine(machine);
        assert machineService.getMachineById((long)3).getMachine_name() == "oppdatert test maskin";
    }

}
