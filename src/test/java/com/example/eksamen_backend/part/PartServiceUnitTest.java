package com.example.eksamen_backend.part;

import com.example.eksamen_backend.model.Part;
import com.example.eksamen_backend.repository.PartRepository;
import com.example.eksamen_backend.service.PartService;
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
public class PartServiceUnitTest {
    @MockBean
    private PartRepository partRepo;
    @Autowired
    private PartService partService;

    @Test
    void shouldGetPartById() {
        var part = new Part("test part");
        when(partRepo.findById(1L)).thenReturn(Optional.of(part));
        var foundPart = partService.getPartById(1L);
        assert foundPart.getPart_name() == "test part";
    }

    //https://stackoverflow.com/questions/55448188/spring-boot-pagination-mockito-repository-findallpageable-returns-null
    @Test
    void shouldGetPartsPageable() {
        var part = new Part("test part");

        Page<Part> parts = new PageImpl<>(List.of(part));
        when(partRepo.findAll(org.mockito.ArgumentMatchers.isA(Pageable.class))).thenReturn(parts);
        var foundPart = partService.getPartsPageable(0);
        assert foundPart.equals( parts.stream().toList());
    }
    @Test
    void shouldAddPart() {
        var part = new Part("test part");
        when(partRepo.save(part)).thenReturn(part);
        var savedPart = partService.addPart(part);
        assert savedPart.getPart_name() == "test part";
    }
    @Test
    void shouldDeletePart() {
        var part = new Part("slett meg");
        when(partRepo.save(part)).thenReturn(part);
        part = partService.addPart(part);
        when(partRepo.findById(part.getPart_id())).thenReturn(Optional.of(part));
        partService.deletePart(part.getPart_id());
        verify(partRepo).deleteById(part.getPart_id());
    }
    @Test
    void shouldUpdatePart() {
        var part = new Part("test part");
        when(partRepo.save(part)).thenReturn(part);
        var savedPart = partService.addPart(part);
        savedPart.setPart_name("test part2");
        when(partRepo.save(savedPart)).thenReturn(savedPart);
        var updatedPart = partService.updatePart(savedPart);
        assert updatedPart.getPart_name() == "test part2";
    }
}
