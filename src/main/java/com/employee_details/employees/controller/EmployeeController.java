package com.employee_details.employees.controller;

import com.employee_details.employees.entity.Employee;
import com.employee_details.employees.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public String listEmployees() {
        return "list-employees";
    }

    @GetMapping("/data")
    @ResponseBody
    public List<Employee> getEmployees() {
        return employeeService.findAllEmployees();
    }

    @GetMapping("/add")
    public String addEmployee(Model model) {
        model.addAttribute("employee", new Employee());
        return "employee-form";
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveEmployee(@ModelAttribute Employee employee) {
        employeeService.saveEmployee(employee);
        return ResponseEntity.ok("Employee saved successfully");
    }


    @GetMapping("/edit/{employeeId}")
    public String updateEmployee(@PathVariable Integer employeeId, Model model) {
        Employee employee=employeeService.getEmployeeById(employeeId);
        model.addAttribute("employee", employee);
        return "employee-form";
    }

    @PostMapping("/delete/{id}")
    @ResponseBody
    public String deleteEmployee(@PathVariable Integer id) {
        employeeService.deleteEmployeeById(id);
        return "Employee " + id + " deleted successfully";
    }
}
