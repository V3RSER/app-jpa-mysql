package com.sofka.app.controllers;

import com.sofka.app.models.Role;
import com.sofka.app.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping
    public ResponseEntity<List<Role>> getAllRoles() {
        try {
            List<Role> roles = roleService.getRoles();
            if (roles.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(roles, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Role> getRoleByID(@PathVariable("id") Long id) {
        Optional<Role> roleByID = this.roleService.getRoleById(id);

        if (roleByID.isPresent()) {
            return new ResponseEntity<>(roleByID.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/add")
    public ResponseEntity<Role> createTutorial(@RequestBody Role role) {
        try {
            Role roleSaved = roleService.saveRole(role);
            return new ResponseEntity<>(roleSaved, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Role> updateRole(@PathVariable("id") long id, @RequestBody Role role) {
        try {
            return new ResponseEntity<>(this.roleService.updateRole(id, role), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<String> deleteRoleByID(@PathVariable("id") long id) {
        try {
            roleService.removeRole(id);
            return new ResponseEntity<>("Role DELETE!! ", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }
}