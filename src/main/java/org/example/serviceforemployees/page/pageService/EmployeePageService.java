package org.example.serviceforemployees.page.pageService;

import lombok.RequiredArgsConstructor;
import org.example.serviceforemployees.model.Employee;
import org.example.serviceforemployees.page.pageDTO.EmployeePageDto;
import org.example.serviceforemployees.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeePageService {

    private final EmployeeService employeeService;

    public List<EmployeePageDto> findAll() {
        return employeeService.findAll().stream()
                .map(this::convert)
                .toList();
    }

    public Optional<EmployeePageDto> findById(Long id) {
        return employeeService.findById(id)
                .map(this::convert);
    }

    private EmployeePageDto convert(Employee employee) {
        EmployeePageDto employeePageDto = new EmployeePageDto();

        employeePageDto.setName(employee.getName());
        employeePageDto.setId(employee.getId());

        return employeePageDto;
    }
}
