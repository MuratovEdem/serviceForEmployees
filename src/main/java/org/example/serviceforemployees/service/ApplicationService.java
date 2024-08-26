package org.example.serviceforemployees.service;

import lombok.RequiredArgsConstructor;
import org.example.serviceforemployees.model.Application;
import org.example.serviceforemployees.model.Client;
import org.example.serviceforemployees.model.Employee;
import org.example.serviceforemployees.repository.ApplicationRepository;
import org.example.serviceforemployees.repository.ClientRepository;
import org.example.serviceforemployees.repository.EmployeeRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ApplicationService {
    private final ApplicationRepository applicationRepository;
    private final ClientRepository clientRepository;
    private final EmployeeRepository employeeRepository;

    public List<Application> findApplicationsByClientId(Long id) {
        return applicationRepository.findApplicationsByClientId(id);
    }

    public List<Application> findApplicationsByEmployeeId(Long id) {
        return applicationRepository.findByEmployeeId(id);
    }

    public List<Application> findApplicationsCurrentLoggedUserByStatus(Boolean isCompleted) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (Objects.isNull(isCompleted)) {
            return applicationRepository.findByEmployeeLogin(authentication.getName());
        }

        return applicationRepository.findByEmployeeLoginWhereIsCompleted(authentication.getName(), isCompleted);
    }

    public Optional<Application> findById(Long id) {
        return applicationRepository.findById(id);
    }

    public Application create(Application application) {
        if (Objects.isNull(application.getClient())){
            throw new IllegalArgumentException("Client must not be null");
        }

        Long clientId = application.getClient().getId();
        Optional<Client> client = clientRepository.findById(clientId);

        if (client.isEmpty()){
            throw new NoSuchElementException("Client with id " + clientId + " does not exists");
        }

        application.setClient(client.get());

        if (Objects.isNull(application.getEmployees())) {
            throw new IllegalArgumentException("Employees must not be null");
        }

        Set<Employee> employeeSet = application.getEmployees();
        Set<Employee> employeesResult = new HashSet<>();

        for (Employee employee: employeeSet) {
            Optional<Employee> employeeOptional = employeeRepository.findById(employee.getId());
            if (employeeOptional.isEmpty()) {
                throw new NoSuchElementException("Employee with id " + employee.getId() + " does not exists");
            }

            employeesResult.add(employeeOptional.get());
        }

        application.setEmployees(employeesResult);

        application.setCreatedAt(LocalDate.now());

        if (Objects.isNull(application.getDeadline())) {
            application.setDeadline(application.getCreatedAt().plusDays(7));
        }

        return applicationRepository.save(application);
    }

    public void delete(Long id) {
        applicationRepository.deleteById(id);
    }
}
