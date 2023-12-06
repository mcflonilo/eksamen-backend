package com.example.eksamen_backend.controller;

import com.example.eksamen_backend.model.Sub_assembly;
import com.example.eksamen_backend.service.SubAssemblyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subAssembly")
public class SubAssemblyController {
    private final SubAssemblyService subAssemblyService;

    @Autowired
    public SubAssemblyController(SubAssemblyService subAssemblyService) {
        this.subAssemblyService = subAssemblyService;
    }

    @GetMapping("/page/{page}")
    public List<Sub_assembly> getSubAssemblyPageable(@PathVariable int page) {
        return subAssemblyService.getSubAssemblyPageable(page);
    }
    @GetMapping("/{id}")
    public Sub_assembly getSubAssemblyById(@PathVariable Long id) {
        return subAssemblyService.getSubAssemblyById(id);
    }
    @PostMapping
    public Sub_assembly addSubAssembly(@RequestBody Sub_assembly subAssembly) {
        return subAssemblyService.addSubAssembly(subAssembly);
    }
    @DeleteMapping("/{id}")
    public void deleteSubAssembly(@PathVariable Long id) {
        subAssemblyService.deleteSubAssembly(id);
    }
    @PutMapping
    public Sub_assembly updateSubAssembly(@RequestBody Sub_assembly subAssembly) {
        return subAssemblyService.updateSubAssembly(subAssembly);
    }
}
