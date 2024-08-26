package org.example.serviceforemployees.repository;

import org.example.serviceforemployees.model.Application;
import org.example.serviceforemployees.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByName(String name);

    // TODO: findAll()
}
