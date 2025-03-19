package com.employee_details.employees.dao;

import com.employee_details.employees.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository {
    void saveEmployee(Employee employee);
    Employee getEmployeeById(Integer id);
    List<Employee> findAllEmployees();
    void deleteEmployeeById(Integer id);
    Optional<Employee> getEmployeeByEmail(String email);
}
