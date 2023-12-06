package com.example.eksamen_backend.service;

import com.example.eksamen_backend.model.Sub_assembly;
import com.example.eksamen_backend.repository.SubAssemblyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubAssemblyService {
    private final SubAssemblyRepository subAssemblyRepository;

    @Autowired
    public SubAssemblyService(SubAssemblyRepository subAssemblyRepository) {
        this.subAssemblyRepository = subAssemblyRepository;
    }
    //get one by id
    public Sub_assembly getSubAssemblyById(Long id) {
        return subAssemblyRepository.findById(id).orElse(null);
    }
    //get all with pagination
    public List<Sub_assembly> getSubAssemblyPageable(int pageNumber) {
        return subAssemblyRepository.findAll(PageRequest.of(pageNumber, 10)).stream().toList();
    }
    //create one
    public Sub_assembly addSubAssembly(Sub_assembly subAssembly) {
        return subAssemblyRepository.save(subAssembly);
    }
    //delete one
    public void deleteSubAssembly(Long id) {
        subAssemblyRepository.deleteById(id);
    }
    //update one
    public Sub_assembly updateSubAssembly(Sub_assembly subAssembly) {
        return subAssemblyRepository.save(subAssembly);
    }


}
