package com.example.eksamen_backend.customer;

import com.example.eksamen_backend.model.Customer;
import com.example.eksamen_backend.repository.CustomerRepository;
import com.example.eksamen_backend.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@SpringBootTest(properties = {"command.line.runner.enabled=false"})
public class CustomerServiceUnitTest {
    @MockBean
    private CustomerRepository customerRepo;
    @Autowired
    private CustomerService customerService;

    @Test
    void shouldGetCustomerById() {
        var customer = new Customer("test unitTestesen");
        when(customerRepo.findById(1L)).thenReturn(Optional.of(customer));
        var foundCustomer = customerService.getCustomerById(1L);
        assert foundCustomer.getCustomerName() == "test unitTestesen";
    }

    //https://stackoverflow.com/questions/55448188/spring-boot-pagination-mockito-repository-findallpageable-returns-null
    @Test
    void shouldGetCustomersPageable() {
        var customer = new Customer("test unitTestesen");

        Page<Customer> customers = new PageImpl<>(List.of(customer));
        when(customerRepo.findAll(org.mockito.ArgumentMatchers.isA(Pageable.class))).thenReturn(customers);
        var foundCustomer = customerService.getCustomersPageable(0);
        assert foundCustomer.equals( customers.stream().toList());
    }
    @Test
    void shouldAddCustomer() {
        var customer = new Customer("test unitTestesen");
        when(customerRepo.save(customer)).thenReturn(customer);
        var savedCustomer = customerService.addCustomer(customer);
        assert savedCustomer.getCustomerName() == "test unitTestesen";
    }
    @Test
    void shouldDeleteCustomer() {
        var customer = new Customer("slett meg", 1);;
        when(customerRepo.save(customer)).thenReturn(customer);
        customer = customerService.addCustomer(customer);
        when(customerRepo.findById(customer.getCustomer_id())).thenReturn(Optional.of(customer));
        customerService.deleteCustomer(customer.getCustomer_id());
        verify(customerRepo).deleteById(customer.getCustomer_id());
    }
    @Test
    void shouldUpdateCustomer() {
        var customer = new Customer("test unitTestesen");
        when(customerRepo.save(customer)).thenReturn(customer);
        var savedCustomer = customerService.addCustomer(customer);
        savedCustomer.setName("test unitTestesen2");
        when(customerRepo.save(savedCustomer)).thenReturn(savedCustomer);
        var updatedCustomer = customerService.updateCustomer(savedCustomer);
        assert updatedCustomer.getCustomerName() == "test unitTestesen2";
    }
}
