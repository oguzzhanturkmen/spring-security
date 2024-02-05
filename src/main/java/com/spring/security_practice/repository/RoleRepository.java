package com.spring.security_practice.repository;

import com.spring.security_practice.domain.Role;
import com.spring.security_practice.domain.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(UserRole name);
}
