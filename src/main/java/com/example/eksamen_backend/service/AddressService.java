package com.example.eksamen_backend.service;

import com.example.eksamen_backend.model.Address;
import com.example.eksamen_backend.model.Customer;
import com.example.eksamen_backend.repository.AddressRepository;
import com.example.eksamen_backend.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {
    private final AddressRepository addressRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }
    //get one by id
    public Address getAddressById(Long id) {
        return addressRepository.findById(id).orElse(null);
    }
    //get all with pagination
    public List<Address> getAddressPageable(int pageNumber) {
        return addressRepository.findAll(PageRequest.of(pageNumber, 10)).stream().toList();
    }
    //create one
    public Address addAddress(Address address) {
        return addressRepository.save(address);
    }
    //delete one
    public void deleteAddress(Long id) {
        addressRepository.deleteById(id);
    }
    //update one
    public Address updateAddress(Address address) {
        return addressRepository.save(address);
    }


}
