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
        assert parts.size() == 7;
        System.out.println(parts.get(6).getPart_name());
        assert parts.get(6).getPart_name() == "test part";
        partService.deletePart( parts.get(6).getPart_id());
        parts = partService.getPartsPageable(0);
        assert parts.size() == 6;
    }
    @Test
    @Transactional
    void shouldUpdateAndGetById() {
        var Part = new Part("test part");
        partService.addPart(Part);
        var part = partService.getPartById((long) 3);
        part.setPart_name("oppdatert test part");
        partService.updatePart(part);
        assert partService.getPartById((long)3).getPart_name() == "oppdatert test part";
    }
}
