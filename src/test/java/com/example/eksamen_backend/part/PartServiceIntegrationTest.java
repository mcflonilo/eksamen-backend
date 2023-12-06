package com.example.eksamen_backend.part;

import com.example.eksamen_backend.model.Part;
import com.example.eksamen_backend.service.PartService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PartServiceIntegrationTest {
    @Autowired
    private PartService partService;

    @Test
    @Transactional
    void shouldAddAndDeletePart() {
        var Part = new Part("test part");
        partService.addPart(Part);
        var parts = partService.getPartsPageable(0);
        System.out.println(parts.size());
        int size = parts.size();

        System.out.println(parts.get(size-1).getPart_name());
        assert parts.get(size-1).getPart_name() == "test part";
        partService.deletePart( parts.get(size-1).getPart_id());
        parts = partService.getPartsPageable(0);
        assert parts.size() == size-1;
    }
    @Test
    @Transactional
    void shouldUpdateAndGetById() {
        var Part = new Part("test part");
        var part = partService.addPart(Part);
        part.setPart_name("oppdatert test part");
        partService.updatePart(part);
        assert partService.getPartById(part.getPart_id()).getPart_name() == "oppdatert test part";
    }
}
