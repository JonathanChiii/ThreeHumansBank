package com.banking.service;

import com.banking.model.Manager;
import com.banking.model.Staff;

import java.util.List;

public interface StaffService {

    Staff findById(Long id);
    Staff findByUsername(String username);
    List<Staff> findAll();
    List<Staff> findByManager(Manager manager);
    List<Staff> findEnabledStaff();
    List<Staff> findDisabledStaff();
    Staff save(Staff staff);
    Staff update(Staff staff);
}
