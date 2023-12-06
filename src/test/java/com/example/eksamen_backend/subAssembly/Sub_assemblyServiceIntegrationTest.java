package com.example.eksamen_backend.subAssembly;

import com.example.eksamen_backend.model.Sub_assembly;
import com.example.eksamen_backend.service.SubAssemblyService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Sub_assemblyServiceIntegrationTest {
    @Autowired
    private SubAssemblyService subAssemblyService;

    @Test
    @Transactional
    void shouldAddAndDeleteSub_assembly() {
        var Sub_assembly = new Sub_assembly("test sub assembly");
        subAssemblyService.addSubAssembly(Sub_assembly);
        var subAssemblys = subAssemblyService.getSubAssemblyPageable(0);
        System.out.println(subAssemblys.size());
        assert subAssemblys.size() == 5;
        System.out.println(subAssemblys.get(4).getSub_assembly_name());
        assert subAssemblys.get(4).getSub_assembly_name() == "test sub assembly";
        subAssemblyService.deleteSubAssembly( subAssemblys.get(4).getSub_assembly_id());
        subAssemblys = subAssemblyService.getSubAssemblyPageable(0);
        assert subAssemblys.size() == 4;
    }
    @Test
    @Transactional
    void shouldUpdateAndGetById() {
        var Sub_assembly = new Sub_assembly("test sub assembly");
        subAssemblyService.addSubAssembly(Sub_assembly);
        var subAssembly = subAssemblyService.getSubAssemblyById((long) 4);
        subAssembly.setSub_assembly_name("oppdatert test sub assembly");
        subAssemblyService.updateSubAssembly(subAssembly);
        assert subAssemblyService.getSubAssemblyById((long)4).getSub_assembly_name() == "oppdatert test sub assembly";
    }
}
