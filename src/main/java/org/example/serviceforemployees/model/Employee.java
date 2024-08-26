package org.example.serviceforemployees.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.*;

@Data
@Entity
@Table(name = "employees")
@EqualsAndHashCode(of = {"id"})
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    private Integer age;

    @OneToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinTable(name = "employees_applications",
        joinColumns = @JoinColumn(name = "employee_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "application_id", referencedColumnName = "id"))
    @JsonIgnore
    private Set<Application> applications = new HashSet<>();

}