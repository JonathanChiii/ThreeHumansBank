package com.banking.service;

import com.banking.model.Manager;
import com.banking.model.Staff;

import java.util.List;

public interface StaffService {
    List<Staff> findAllStaff();
    Staff findById(Long id);
    Staff findByUsername(String username);
    List<Staff> findByManager(Manager manager);
    Staff save(Staff staff);
    Staff update(Staff staff);
}
