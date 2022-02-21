package com.sofka.app.repositories;

import com.sofka.app.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Se establece que es un repositorio
public interface iRoleJpaRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
