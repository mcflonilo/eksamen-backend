package com.example.eksamen_backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Part {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "part_sequence")
    @SequenceGenerator(name = "part_sequence", sequenceName = "part_seq",initialValue = 1, allocationSize = 1)
    @Column(name = "part_id")
    private Long part_id = 0L;
    @Column(name = "part_name")
    private String part_name;
    public Part(String name) {
        this.part_name = name;
    }
}
