package com.banking.repository;

import com.banking.model.ModelUtility.ERole;
import com.banking.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
    Optional<Role> findRoleByName(ERole name);
}
