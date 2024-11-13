package com.example.longevitysync.controller;

import com.example.longevitysync.model.LSRole;
import com.example.longevitysync.repository.LSRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class LSRoleController {

    @Autowired
    private LSRoleRepository roleRepository;

    @GetMapping
    public List<LSRole> getAllRoles() {
        return roleRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<LSRole> createRole(@RequestBody LSRole role) {
        LSRole createdRole = roleRepository.save(role);  // Save the role
        return ResponseEntity
                .created(URI.create("/api/roles/" + createdRole.getId()))  // Set the Location header
                .body(createdRole); 
    }

    @GetMapping("/{id}")
    public ResponseEntity<LSRole> getRoleById(@PathVariable Long id) {
        return roleRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<LSRole> updateRole(@PathVariable Long id, @RequestBody LSRole updatedRole) {
        return roleRepository.findById(id)
                .map(role -> {
                    role.setName(updatedRole.getName());
                    return ResponseEntity.ok(roleRepository.save(role));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable Long id) {
        if (roleRepository.existsById(id)) {
            roleRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
