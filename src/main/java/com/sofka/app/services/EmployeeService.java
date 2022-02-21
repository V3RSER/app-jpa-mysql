package com.sofka.app.services;
import com.sofka.app.models.Employee;
import com.sofka.app.repositories.iEmployeeJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service // Se establece que es un sevicio
public class EmployeeService {
    @Autowired // Instancia automáticamente EmployeeRepository
    iEmployeeJpaRepository EmployeeRepository;

    @Autowired // Instancia automáticamente iEmployeeJpaRepository
    iEmployeeJpaRepository iEmployeeJpaRepository;

    public List<Employee> getEmployees() {
        return iEmployeeJpaRepository.findAll();
    }

    public Optional<Employee> getEmployeeById(Long id) {
        return this.iEmployeeJpaRepository.findById(id);
    }

    public Employee saveEmployee(Employee employee) {
        return this.iEmployeeJpaRepository.save(employee);
    }

    public Employee updateEmployee(Long id, Employee employee) {
        Optional<Employee> oldEmployee = this.iEmployeeJpaRepository.findById(id);

        if (oldEmployee.isPresent()) {
            employee.setId(id);
            employee.setFirstName(employee.getFirstName() == null ? oldEmployee.get().getFirstName() : employee.getFirstName());
            employee.setLastName(employee.getLastName() == null ? oldEmployee.get().getLastName() : employee.getLastName());
            employee.setRole(employee.getRole() == null ? oldEmployee.get().getRole() : employee.getRole());
            return this.iEmployeeJpaRepository.save(employee);
        }
        return employee;
    }

    public boolean removeEmployee(Long id) {
        try {
            this.iEmployeeJpaRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}