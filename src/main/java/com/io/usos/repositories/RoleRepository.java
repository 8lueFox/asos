package com.io.usos.repositories;

import com.io.usos.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findRoleByType(Role.Types type);
}
