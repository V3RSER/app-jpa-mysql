package com.sofka.app.services;

import com.sofka.app.models.Role;
import com.sofka.app.repositories.iRoleJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service // Se establece que es un sevicio
public class RoleService {
    @Autowired // Instancia automáticamente RoleRepository
    iRoleJpaRepository RoleRepository;

    @Autowired // Instancia automáticamente iRoleJpaRepository
    iRoleJpaRepository iRoleJpaRepository;

    public List<Role> getRoles() {
        return iRoleJpaRepository.findAll();
    }

    public Optional<Role> getRoleById(Long id) {
        return this.iRoleJpaRepository.findById(id);
    }

    public Role saveRole(Role role) {
        return this.iRoleJpaRepository.save(role);
    }

    public Role updateRole(Long id, Role role) {
        Optional<Role> oldRole = this.iRoleJpaRepository.findById(id);

        if (oldRole.isPresent()) {
            role.setId(id);
            role.setName(role.getName() == null ? oldRole.get().getName() : role.getName());
             return this.iRoleJpaRepository.save(role);
        }
        return role;
    }
    
    public boolean removeRole(Long id) {
        try {
            this.iRoleJpaRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}