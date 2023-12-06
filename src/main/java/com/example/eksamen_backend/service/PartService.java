package com.example.eksamen_backend.service;

import com.example.eksamen_backend.model.Part;
import com.example.eksamen_backend.repository.PartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartService {
    private final PartRepository partRepository;

    @Autowired
    public PartService(PartRepository partRepository) {
        this.partRepository = partRepository;
    }
    //get one by id
    public Part getPartById(Long id) {
        return partRepository.findById(id).orElse(null);
    }
    //get all with pagination
    public List<Part> getPartsPageable(int pageNumber) {
        return partRepository.findAll(PageRequest.of(pageNumber, 10)).stream().toList();
    }
    //create one
    public Part addPart(Part part) {
        return partRepository.save(part);
    }
    //delete one
    public void deletePart(Long id) {
        partRepository.deleteById(id);
    }
    //update one
    public Part updatePart(Part part) {
        return partRepository.save(part);
    }


}
