package org.example.serviceforemployees.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "applications")
@EqualsAndHashCode(of = {"id"})
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Client client;

    @Column(length = 5000)
    private String text;

    private LocalDate createdAt;

    private LocalDate deadline;

    @Column(name = "is_completed")
    private Boolean isCompleted;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinTable(name = "employees_applications",
        joinColumns = @JoinColumn(name = "application_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "employee_id", referencedColumnName = "id"))
    @JsonIgnore
    private Set<Employee> employees = new HashSet<>();

}
