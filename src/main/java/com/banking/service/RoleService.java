package com.banking.service;

import com.banking.model.ModelUtility.ERole;
import com.banking.model.Role;

public interface RoleService {
    Role findByName(ERole name);
}
