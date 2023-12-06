package com.example.eksamen_backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Entity
@Getter
@Setter
@Table(name = "sub_assembly")
public class Sub_assembly {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sub_assembly_generator")
    @SequenceGenerator(name = "sub_assembly_generator", sequenceName = "sub_assembly_seq", initialValue = 1, allocationSize = 1)
    @Column(name = "sub_assembly_id")
    private Long sub_assembly_id = 0L;
    @Column(name = "sub_assembly_name")
    private String sub_assembly_name;

    @ManyToMany(cascade = CascadeType.ALL)
    @JsonIgnoreProperties("sub_assemblies")
    @JoinTable(
            name = "Sub_assembly_part",
            joinColumns = @JoinColumn(name = "sub_assembly_id"),
            inverseJoinColumns = @JoinColumn(name = "part_id")
    )
    private List<Part> parts= new ArrayList<>();

    public void setPart(Part part) {
        this.parts.add(part);
    }
    public List<Part> getParts() {
        return parts;
    }
    public Sub_assembly(String name) {
        this.sub_assembly_name = name;
    }

    public Sub_assembly() {
    }
}
