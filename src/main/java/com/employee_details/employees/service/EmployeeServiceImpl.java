package com.employee_details.employees.service;

import com.employee_details.employees.dao.EmployeeRepository;
import com.employee_details.employees.entity.Employee;
import com.employee_details.employees.exception.EmployeeNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService{
    private final EmployeeRepository employeeRepository;
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository=employeeRepository;
    }

    @Override
    public void saveEmployee(Employee employee) {
        var existingEmployee = getEmployeeByEmail(employee.getEmailId());
        if(!existingEmployee.getEmployeeId().equals(employee.getEmployeeId()) && existingEmployee!=null)
            throw new EmployeeNotFoundException("Employee already exists with same email");
        employeeRepository.saveEmployee(employee);
    }

    @Override
    public Employee getEmployeeById(Integer id) {
        return employeeRepository.getEmployeeById(id);
    }

    @Override
    public List<Employee> findAllEmployees() {
        return employeeRepository.findAllEmployees();
    }

    @Override
    public void deleteEmployeeById(Integer id) {
        employeeRepository.deleteEmployeeById(id);
    }

    @Override
    public Employee getEmployeeByEmail(String email) {
        return employeeRepository.getEmployeeByEmail(email).orElse(null);
    }
}
