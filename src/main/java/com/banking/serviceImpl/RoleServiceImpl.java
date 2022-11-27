package com.banking.serviceImpl;

import com.banking.model.ModelUtility.ERole;
import com.banking.model.Role;
import com.banking.repository.RoleRepository;
import com.banking.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleRepository roleRepository;

    @Override
    public Role findByName(ERole name) {
        return roleRepository.findRoleByName(name).orElse(null);
    }
}
