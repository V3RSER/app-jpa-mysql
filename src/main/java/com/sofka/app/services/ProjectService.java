package com.sofka.app.services;

import com.sofka.app.models.Project;
import com.sofka.app.repositories.iProjectJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service // Se establece que es un sevicio
public class ProjectService {
    @Autowired // Instancia automáticamente ProjectRepository
    iProjectJpaRepository ProjectRepository;

    @Autowired // Instancia automáticamente iProjectJpaRepository
    iProjectJpaRepository iProjectJpaRepository;

    public List<Project> getProjects() {
        return iProjectJpaRepository.findAll();
    }

    public Optional<Project> getProjectById(Long id) {
        return this.iProjectJpaRepository.findById(id);
    }

    public Project saveProject(Project project) {
        return this.iProjectJpaRepository.save(project);
    }

    public Project updateProject(Long id, Project project) {
        Optional<Project> oldProject = this.iProjectJpaRepository.findById(id);

        if (oldProject.isPresent()) {
            project.setId(id);
            project.setName(project.getName() == null ? oldProject.get().getName() : project.getName());
            return this.iProjectJpaRepository.save(project);
        }
        return project;
    }

    public boolean removeProject(Long id) {
        try {
            this.iProjectJpaRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}