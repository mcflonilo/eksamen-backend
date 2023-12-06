package com.example.eksamen_backend.service;

import com.example.eksamen_backend.model.Customer;
import com.example.eksamen_backend.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    //get one by id
    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id).orElse(null);
    }
    //get all with pagination
    public List<Customer> getCustomersPageable(int pageNumber) {
        return customerRepository.findAll(PageRequest.of(pageNumber, 10)).stream().toList();
    }
    //create one
    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }
    //delete one
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
    //update one
    public Customer updateCustomer(Customer customer) {
        return customerRepository.save(customer);
    }


}
