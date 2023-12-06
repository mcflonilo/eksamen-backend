package com.example.eksamen_backend.address;

import com.example.eksamen_backend.model.Address;
import com.example.eksamen_backend.service.AddressService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AddressServiceIntegrationTest {
    @Autowired
    private AddressService addressService;

    @Test
    @Transactional
    void shouldAddAndDeleteAddress() {
        var Address = new Address("testGata 1");
        addressService.addAddress(Address);
        var address = addressService.getAddressPageable(0);
        System.out.println(address.size());
        int size = address.size();
        System.out.println(address.get(size-1).getAddress());
        assert address.get(size-1).getAddress() == "testGata 1";
        addressService.deleteAddress( address.get(size-1).getAddress_id());
        address = addressService.getAddressPageable(0);
        assert address.size() == size-1;
    }
    @Test
    @Transactional
    void shouldUpdateAndGetById() {
        var Address = new Address("testGata 1");
        var address = addressService.addAddress(Address);
        address.setAddress("testGata 2");
        addressService.updateAddress(address);
        assert addressService.getAddressById(address.getAddress_id()).getAddress() == "testGata 2";
    }
}
