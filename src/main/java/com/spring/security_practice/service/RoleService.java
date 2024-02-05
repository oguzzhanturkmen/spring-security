package com.spring.security_practice.service;

import com.spring.security_practice.domain.Role;
import com.spring.security_practice.domain.enums.UserRole;
import com.spring.security_practice.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public Role getRoleByType(UserRole userRole) {
        return roleRepository.findByName(userRole)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found." + userRole.name()));
    }
}
