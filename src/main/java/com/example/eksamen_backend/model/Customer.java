package com.example.eksamen_backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Setter
@NoArgsConstructor
public class Customer {

    public Customer(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_sequence")
    @SequenceGenerator(name = "customer_sequence", sequenceName = "customer_seq", initialValue = 1, allocationSize = 1)
    @Column(name = "customer_id")
    private Long customer_id = 0L;

    @Column(name = "customer_name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"machine","customer"})
    @JoinColumn(name = "customer_id")
    private List<Order> orders = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JsonIgnoreProperties("customers")
    @JoinTable(
        name = "customer_address",
        joinColumns = @JoinColumn(name = "customer_id"),
        inverseJoinColumns = @JoinColumn(name = "address_id")
    )
    private List<Address> addresses = new ArrayList<>();

    public Customer(String name, long i) {
        this.name = name;
        this.customer_id = i;
    }

    public int getCustomerId() {
        return customer_id.intValue();
    }

    public String getCustomerName() {
        return name;
    }

    public void setAddress(Address address) {
        this.addresses.add(address);
    }
}
