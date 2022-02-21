package com.sofka.app.repositories;

import com.sofka.app.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Se establece que es un repositorio
public interface iEmployeeJpaRepository extends JpaRepository<Employee, Long> {
    // Devuelve un empleado que coincida con una id pasada como parámetro
    Employee findByEmployeeid(String employeeid);

}
