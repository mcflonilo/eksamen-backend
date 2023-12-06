package com.example.eksamen_backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_sequence")
    @SequenceGenerator(name = "address_sequence", sequenceName = "address_seq", initialValue = 1, allocationSize = 1)
    @Column(name = "address_id")
    private Long address_id = 0L;

    private String address;
    @ManyToMany(mappedBy = "addresses" , cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Customer> customers = new ArrayList<>();

    public Address(String address) {
        this.address = address;
    }
}
