package com.example.eksamen_backend.controller;

import com.example.eksamen_backend.model.Part;
import com.example.eksamen_backend.service.PartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/part")
public class PartController {
    private final PartService partService;

    @Autowired
    public PartController(PartService partService) {
        this.partService = partService;
    }

    @GetMapping("/page/{page}")
    public List<Part> getPartsPageable(@PathVariable int page) {
        return partService.getPartsPageable(page);
    }
    @GetMapping("/{id}")
    public Part getPartById(@PathVariable Long id) {
        return partService.getPartById(id);
    }
    @PostMapping
    public Part addPart(@RequestBody Part part) {
        return partService.addPart(part);
    }
    @DeleteMapping("/{id}")
    public void deletePart(@PathVariable Long id) {
        partService.deletePart(id);
    }
    @PutMapping
    public Part updatePart(@RequestBody Part part) {
        return partService.updatePart(part);
    }
}
