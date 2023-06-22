package com.technicaltest.test.auth.repositories;

import com.technicaltest.test.auth.models.entities.Role;
import com.technicaltest.test.auth.models.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleName(RoleName roleName);
}
