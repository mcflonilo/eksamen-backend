package com.example.eksamen_backend.customer;

import com.example.eksamen_backend.model.Address;
import com.example.eksamen_backend.model.Customer;
import com.example.eksamen_backend.service.CustomerService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CustomerServiceIntegrationTest {
    @Autowired
    private CustomerService customerService;

    @Test
    @Transactional
    void shouldAddAndDeleteCustomer() {
        var Customer = new Customer("test testesen");
        customerService.addCustomer(Customer);
        var customers = customerService.getCustomersPageable(0);
        System.out.println(customers.size());
        int size = customers.size();
        System.out.println(customers.get(size-1).getCustomerName());
        assert customers.get(size-1).getCustomerName() == "test testesen";
        customerService.deleteCustomer( customers.get(size-1).getCustomerId());
        customers = customerService.getCustomersPageable(0);
        assert customers.size() == size-1;
    }
    @Test
    @Transactional
    void shouldUpdateAndGetById() {
        var Customer = new Customer("test testesen");
        var customer = customerService.addCustomer(Customer);
        customer.setName("oppdatert test testesen");
        customerService.updateCustomer(customer);
        assert customerService.getCustomerById(customer.getCustomer_id()).getCustomerName() == "oppdatert test testesen";
    }

    @Test
    @Transactional
    void shouldGetCustomerFromCommandLine() {
        var customers = customerService.getCustomersPageable(0);
        var customer = customers.get(0);
        var order = customer.getOrders().get(0);
        var machine = order.getMachine().get(0);
        var subAssembly = machine.getSub_assemblies().get(0);
        var part = subAssembly.getParts().get(0);
        assert customer.getCustomerName() == "larsern";
        assert machine.getMachine_name() == "car";
        assert subAssembly.getSub_assembly_name() == "gearbox";
        assert part.getPart_name() == "gear";
    }

}
