package com.example.eksamen_backend.subAssembly;

import com.example.eksamen_backend.model.Part;
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
        var part = new Part("test part");
        Sub_assembly.setPart(part);
        subAssemblyService.addSubAssembly(Sub_assembly);
        var subAssemblys = subAssemblyService.getSubAssemblyPageable(0);
        System.out.println(subAssemblys.size());
        int size = subAssemblys.size();
        System.out.println(subAssemblys.get(subAssemblys.size()-1).getSub_assembly_name());
        assert subAssemblys.get(subAssemblys.size()-1).getSub_assembly_name() == "test sub assembly";
        subAssemblyService.deleteSubAssembly( subAssemblys.get(subAssemblys.size()-1).getSub_assembly_id());
        subAssemblys = subAssemblyService.getSubAssemblyPageable(0);
        assert subAssemblys.size() == size-1;
    }
    @Test
    @Transactional
    void shouldUpdateAndGetById() {
        var Sub_assembly = new Sub_assembly("test sub assembly");
        var subAssembly = subAssemblyService.addSubAssembly(Sub_assembly);
        subAssembly.setSub_assembly_name("oppdatert test sub assembly");
        subAssemblyService.updateSubAssembly(subAssembly);
        assert subAssemblyService.getSubAssemblyById((subAssembly.getSub_assembly_id())).getSub_assembly_name() == "oppdatert test sub assembly";
    }
}
