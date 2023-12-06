package com.example.eksamen_backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "machine")
public class Machine {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "machine_sequence")
    @SequenceGenerator(name = "machine_sequence", sequenceName = "machine_seq", initialValue = 1, allocationSize = 1)
    @Column(name = "machine_id")
    private Long machine_id = 0L;
    @Column(name = "machine_name")
    private String machine_name;


    @ManyToMany(cascade = CascadeType.ALL)
    @JsonIgnoreProperties("machines")
    @JoinTable(
        name = "machine_Sub_assembly",
        joinColumns = @JoinColumn(name = "machine_id"),
        inverseJoinColumns = @JoinColumn(name = "sub_assembly_id")
    )
    private List<Sub_assembly> sub_assemblies= new ArrayList<>();

    public void setSub_assemblies(Sub_assembly sub_assemblies) {
        this.sub_assemblies.add(sub_assemblies);
    }
    public List<Sub_assembly> getSub_assemblies() {
        return sub_assemblies;
    }

    public Machine(String name ) {
        this.machine_name = name;
    }
    public Machine() {
    }
}
