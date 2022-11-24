package com.banking.repository;

import com.banking.model.ModelUtility.ERole;
import com.banking.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
    Role findByName(ERole name);
}
