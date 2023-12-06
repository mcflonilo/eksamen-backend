package com.example.eksamen_backend.address;

import com.example.eksamen_backend.model.Address;
import com.example.eksamen_backend.repository.AddressRepository;
import com.example.eksamen_backend.service.AddressService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(properties = {"command.line.runner.enabled=false"})
public class AddressServiceUnitTest {
    @MockBean
    private AddressRepository addressRepo;
    @Autowired
    private AddressService addressService;

    @Test
    void shouldGetAddressById() {
        var address = new Address("testgata 1");
        when(addressRepo.findById(1L)).thenReturn(Optional.of(address));
        var foundAddress = addressService.getAddressById(1L);
        assert foundAddress.getAddress() == "testgata 1";
    }

    //https://stackoverflow.com/questions/55448188/spring-boot-pagination-mockito-repository-findallpageable-returns-null
    @Test
    void shouldGetAddressPageable() {
        var address = new Address("testgata 1");

        Page<Address> addresses = new PageImpl<>(List.of(address));
        when(addressRepo.findAll(org.mockito.ArgumentMatchers.isA(Pageable.class))).thenReturn(addresses);
        var foundAddress = addressService.getAddressPageable(0);
        assert foundAddress.equals( addresses.stream().toList());
    }
    @Test
    void shouldAddAddress() {
        var address = new Address("testgata 1");
        when(addressRepo.save(address)).thenReturn(address);
        var savedAddress = addressService.addAddress(address);
        assert savedAddress.getAddress() == "testgata 1";
    }
    @Test
    void shouldDeleteAddress() {
        var address = new Address("slett meg");;
        when(addressRepo.save(address)).thenReturn(address);
        address = addressService.addAddress(address);
        when(addressRepo.findById(address.getAddress_id())).thenReturn(Optional.of(address));
        addressService.deleteAddress(address.getAddress_id());
        verify(addressRepo).deleteById(address.getAddress_id());
    }
    @Test
    void shouldUpdateAddress() {
        var address = new Address("testgata 1");
        when(addressRepo.save(address)).thenReturn(address);
        var savedAddress = addressService.addAddress(address);
        savedAddress.setAddress("testgata 12");
        when(addressRepo.save(savedAddress)).thenReturn(savedAddress);
        var updatedAddress = addressService.updateAddress(savedAddress);
        assert updatedAddress.getAddress() == "testgata 12";
    }
}
