package com.sofka.app.repositories;

import com.sofka.app.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Se establece que es un repositorio
public interface iProjectJpaRepository extends JpaRepository<Project, Long> {
    Project findByName(String name);
}
