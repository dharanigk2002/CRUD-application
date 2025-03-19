package com.employee_details.employees.dao;


import com.employee_details.employees.entity.Employee;
import com.employee_details.employees.exception.EmployeeNotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveEmployee(Employee employee) {
        if(employee.getEmployeeId()==null)
            entityManager.persist(employee);
        else
            entityManager.merge(employee);
    }

    @Override
    public Employee getEmployeeById(Integer id) {
        Employee employee=entityManager.find(Employee.class, id);
        if(employee==null) throw new EmployeeNotFoundException("Employee with id " + id + " not found");
        return employee;
    }

    @Override
    public List<Employee> findAllEmployees() {
        TypedQuery<Employee> query=entityManager.createQuery("FROM Employee", Employee.class);
        List<Employee> employees=query.getResultList();
        return employees;
    }

    @Override
    public void deleteEmployeeById(Integer id) {
        Employee employee=getEmployeeById(id);
        if(employee==null) throw new EmployeeNotFoundException("Employee with id " + id + " not found");
        entityManager.remove(employee);
    }

    @Override
    public Optional<Employee> getEmployeeByEmail(String email) {
        try {
            TypedQuery<Employee> query = entityManager.createQuery(
                    "SELECT e FROM Employee e WHERE e.emailId = :emailId", Employee.class);
            query.setParameter("emailId", email);
            return Optional.ofNullable(query.getSingleResult());
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
