package com.employee_details.employees.service;

import com.employee_details.employees.entity.Employee;

import java.util.List;

public interface EmployeeService {
    void saveEmployee(Employee employee);
    Employee getEmployeeById(Integer id);
    List<Employee> findAllEmployees();
    void deleteEmployeeById(Integer id);
    Employee getEmployeeByEmail(String email);
}
