package org.example.serviceforemployees.repository;

import org.example.serviceforemployees.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
