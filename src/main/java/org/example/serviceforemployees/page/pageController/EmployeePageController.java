package org.example.serviceforemployees.page.pageController;


import lombok.RequiredArgsConstructor;
import org.example.serviceforemployees.page.pageDTO.EmployeePageDto;
import org.example.serviceforemployees.page.pageService.EmployeePageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Controller
@RequestMapping("/home/employees")
@RequiredArgsConstructor
public class EmployeePageController {

    private final EmployeePageService employeePageService;

    @GetMapping
    public String getAllEmployeesPage(Model model) {
        List<EmployeePageDto> employees = employeePageService.findAll();
        model.addAttribute("employees", employees);
        return "employees-page.html";

    }

    @GetMapping("/{id}")
    public String getEmployeePage(@PathVariable Long id, Model model) {
        Optional<EmployeePageDto> timesheetOpt = employeePageService.findById(id);
        if (timesheetOpt.isEmpty()) {
            throw new NoSuchElementException();
        }

        model.addAttribute("employee", timesheetOpt.get());
        return "employee-page.html";
    }
}
