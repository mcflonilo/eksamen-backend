package com.example.eksamen_backend.controller;

import com.example.eksamen_backend.model.Address;
import com.example.eksamen_backend.model.Customer;
import com.example.eksamen_backend.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.ContentCachingRequestWrapper;

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
