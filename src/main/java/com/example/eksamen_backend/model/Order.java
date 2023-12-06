package com.example.eksamen_backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_generator")
    @SequenceGenerator(name = "order_generator", sequenceName = "order_seq", initialValue = 1, allocationSize = 1)
    @Column(name = "order_id")
    private Long orderId = 0L;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonIgnoreProperties({"orders", "addresses"})
    private Customer customer;

    @OneToMany(cascade = CascadeType.ALL)
    @Column(name = "machines")
    private List<Machine> machines = new ArrayList<>();

    public void setMachine(Machine machine) {
        this.machines.add(machine);
    }

    public List<Machine> getMachine() {
        return machines;
    }
}
