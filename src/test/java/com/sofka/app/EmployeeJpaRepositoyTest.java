package com.sofka.app;

import com.sofka.app.models.Employee;
import com.sofka.app.repositories.IEmployeeJpaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest // Se establece que se encargue únicamente de test JPA
@AutoConfigureTestDatabase(replace = Replace.NONE) // Permite realizar el test en una DB sin modificarla
// @AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2) // Emula una DB para realizar las pruebas
public class EmployeeJpaRepositoyTest {
    @Autowired // Instancia el repositorio
    private IEmployeeJpaRepository repo;

    @Test // Se establece que la clase será de pruebas
    public void saveEmployee() {
        Employee john = new Employee("John", "Smith", "empl123");
        Employee claire = new Employee("Claire", "Simpson", "empl124");

        repo.save(john);
        repo.save(claire);
        repo.flush();

        assertEquals(2, repo.findAll().size());
    }
}
