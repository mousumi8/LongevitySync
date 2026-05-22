package com.example.longevitysync.repository;

import com.example.longevitysync.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface LSRoleRepository extends JpaRepository<LSRole, Long> {
    Optional<LSRole> findByNameIgnoreCase(String name);
}
