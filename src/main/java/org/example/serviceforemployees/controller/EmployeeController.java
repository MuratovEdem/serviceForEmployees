package org.example.serviceforemployees.controller;

import lombok.RequiredArgsConstructor;
import org.example.serviceforemployees.model.Application;
import org.example.serviceforemployees.model.Employee;
import org.example.serviceforemployees.service.ApplicationService;
import org.example.serviceforemployees.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;
    private final ApplicationService applicationService;

    @GetMapping
    public ResponseEntity<List<Employee>> getALL() {
        try {
            return ResponseEntity.ok(employeeService.findAll());
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> get(@PathVariable Long id) {
        Optional<Employee> ts = employeeService.findById(id);

        return ts.map(employee -> ResponseEntity.status(HttpStatus.OK).body(employee)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/applications")
    public ResponseEntity<List<Application>> getApplicationsByEmployeeId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(applicationService.findApplicationsByEmployeeId(id));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Пример Json запроса
     * {
     *     "name": "newEmployee3",
     *     "age": 28,
     *     "account": {
     *              "login": "newEmployee2",
     *              "password": "password",
     *              "roles": [{"id": 2}]
     *               }
     * }
     */
    @PostMapping
    public ResponseEntity<Employee> create(@RequestBody Employee employee) {
        System.out.println(1);
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.create(employee));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        employeeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
