package com.example.eksamen_backend.subAssembly;

import com.example.eksamen_backend.model.Part;
import com.example.eksamen_backend.model.Sub_assembly;
import com.example.eksamen_backend.repository.SubAssemblyRepository;
import com.example.eksamen_backend.service.SubAssemblyService;
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
public class Sub_assemblyServiceUnitTest {
    @MockBean
    private SubAssemblyRepository sub_assemblyRepo;
    @Autowired
    private SubAssemblyService sub_assemblyService;

    @Test
    void shouldGetSub_assemblyById() {
        var sub_assembly = new Sub_assembly("test unitSubAssembly");
        when(sub_assemblyRepo.findById(1L)).thenReturn(Optional.of(sub_assembly));
        var foundSub_assembly = sub_assemblyService.getSubAssemblyById(1L);
        assert foundSub_assembly.getSub_assembly_name() == "test unitSubAssembly";
    }

    //https://stackoverflow.com/questions/55448188/spring-boot-pagination-mockito-repository-findallpageable-returns-null
    @Test
    void shouldGetSub_assemblysPageable() {
        var sub_assembly = new Sub_assembly("test unitSubAssembly");

        Page<Sub_assembly> sub_assemblys = new PageImpl<>(List.of(sub_assembly));
        when(sub_assemblyRepo.findAll(org.mockito.ArgumentMatchers.isA(Pageable.class))).thenReturn(sub_assemblys);
        var foundSub_assembly = sub_assemblyService.getSubAssemblyPageable(0);
        assert foundSub_assembly.equals( sub_assemblys.stream().toList());
    }
    @Test
    void shouldAddSub_assembly() {
        var sub_assembly = new Sub_assembly("test unitSubAssembly");
        var part = new Part("test unitPart");
        sub_assembly.setPart(part);
        when(sub_assemblyRepo.save(sub_assembly)).thenReturn(sub_assembly);
        var savedSub_assembly = sub_assemblyService.addSubAssembly(sub_assembly);
        assert savedSub_assembly.getSub_assembly_name() == "test unitSubAssembly";
    }
    @Test
    void shouldDeleteSub_assembly() {
        var sub_assembly = new Sub_assembly("slett meg");;
        when(sub_assemblyRepo.save(sub_assembly)).thenReturn(sub_assembly);
        sub_assembly = sub_assemblyService.addSubAssembly(sub_assembly);
        when(sub_assemblyRepo.findById(sub_assembly.getSub_assembly_id())).thenReturn(Optional.of(sub_assembly));
        sub_assemblyService.deleteSubAssembly(sub_assembly.getSub_assembly_id());
        verify(sub_assemblyRepo).deleteById(sub_assembly.getSub_assembly_id());
    }
    @Test
    void shouldUpdateSub_assembly() {
        var sub_assembly = new Sub_assembly("test unitSubAssembly");
        when(sub_assemblyRepo.save(sub_assembly)).thenReturn(sub_assembly);
        var savedSub_assembly = sub_assemblyService.addSubAssembly(sub_assembly);
        savedSub_assembly.setSub_assembly_name("test unitSubAssembly2");
        when(sub_assemblyRepo.save(savedSub_assembly)).thenReturn(savedSub_assembly);
        var updatedSub_assembly = sub_assemblyService.updateSubAssembly(savedSub_assembly);
        assert updatedSub_assembly.getSub_assembly_name() == "test unitSubAssembly2";
    }
}
