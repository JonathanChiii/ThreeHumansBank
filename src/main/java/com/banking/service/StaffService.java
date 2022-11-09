package com.banking.service;

import com.banking.model.Staff;

import java.util.List;

public interface StaffService {
    List<Staff> getAllStaff();
    Staff getById(Long id);
    Staff getByUsername(String username);
    Staff save(Staff staff);
    Staff update(Staff staff);
    void delete(Staff staff);
}
