package org.example.serviceforemployees.service;

import lombok.RequiredArgsConstructor;
import org.example.serviceforemployees.model.Account;
import org.example.serviceforemployees.model.Employee;
import org.example.serviceforemployees.model.RoleEntity;
import org.example.serviceforemployees.repository.AccountRepository;
import org.example.serviceforemployees.repository.EmployeeRepository;
import org.example.serviceforemployees.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final AccountRepository accountRepository;
    private final RoleRepository roleRepository;

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> findById(Long id) {
        return employeeRepository.findById(id);
    }

    public Employee create(Employee employee) {
        if (Objects.isNull(employee.getAccount())) {
            throw new IllegalArgumentException("Account must not be null");
        }
        Account account = employee.getAccount();

        if (Objects.isNull(account.getLogin())) {
            throw new IllegalArgumentException("Login must not be null");
        }

        if (Objects.isNull(account.getPassword())) {
            throw new IllegalArgumentException("Password must not be null");
        }

        if (Objects.isNull(account.getRoles())) {
            throw new IllegalArgumentException("Roles must not be null");
        }

        Set<RoleEntity> roleSet = new HashSet<>();

        for (RoleEntity role: account.getRoles()) {
            if (!accountRepository.existsById(role.getId())) {
                throw new NoSuchElementException("Role with id " + role.getId() + " does not exists");
            }
            Optional<RoleEntity> roleEntityOpt = roleRepository.findById(role.getId());
            if (roleEntityOpt.isEmpty()) {
                throw new NoSuchElementException("Employee with id " + role.getId() + " does not exists");
            }

            roleSet.add(roleEntityOpt.get());
        }

        account.setRoles(roleSet);

        employee.setAccount(accountRepository.save(account));

        return employeeRepository.save(employee);
    }

    public void delete(Long id) {
        accountRepository.deleteById(id);
    }
}
