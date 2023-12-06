package com.example.eksamen_backend.controller;

import com.example.eksamen_backend.model.Address;
import com.example.eksamen_backend.model.Customer;
import com.example.eksamen_backend.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.ContentCachingRequestWrapper;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/page/{page}")
    public List<Customer> getCustomersPageable(@PathVariable int page) {
        return customerService.getCustomersPageable(page);
    }
    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id);

    }


    @PostMapping
    public Customer addCustomer(@RequestBody Customer customer) {
        return customerService.addCustomer(customer);
    }
/*
    @PostMapping
    public Customer addCustomer( HttpServletRequest request) throws IOException {
        //IOUtils.copy(request.getInputStream(), System.out);
        String body = new String(request.getInputStream().readAllBytes());
        Customer customer = new ObjectMapper().readValue(body, Customer.class);
        return customerService.addCustomer(customer);
    }
*/
    @PostMapping("/{id}/addAddress")
    public Customer addAddressToCustomer(@PathVariable Long id, @RequestBody Address address) {
        Customer customer = customerService.getCustomerById(id);
        customer.getAddresses().add(address);
        return customerService.updateCustomer(customer);
    }
    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
    }
    @PutMapping
    public Customer updateCustomer(@RequestBody Customer customer) {
        return customerService.updateCustomer(customer);
    }
}
