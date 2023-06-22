package com.technicaltest.test.auth.services;

import com.technicaltest.test.auth.models.entities.Role;
import com.technicaltest.test.auth.models.enums.RoleName;
import com.technicaltest.test.auth.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Optional<Role> findByRolename(RoleName roleName){
        return roleRepository.findByRoleName(roleName);
    }
}
