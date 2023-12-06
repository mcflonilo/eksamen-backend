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
        assert address.size() == 9;
        System.out.println(address.get(8).getAddress());
        assert address.get(8).getAddress() == "testGata 1";
        addressService.deleteAddress( address.get(8).getAddress_id());
        address = addressService.getAddressPageable(0);
        assert address.size() == 8;
    }
    @Test
    @Transactional
    void shouldUpdateAndGetById() {
        var Address = new Address("testGata 1");
        addressService.addAddress(Address);
        var address = addressService.getAddressById((long) 3);
        address.setAddress("testGata 2");
        addressService.updateAddress(address);
        assert addressService.getAddressById((long)3).getAddress() == "testGata 2";
    }
}
