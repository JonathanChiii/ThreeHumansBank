package com.banking.repository;

import com.banking.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StaffRepository extends JpaRepository<Staff, Long> {
    Staff getStaffByUsername(String username);
    List<Staff> getStaffByFullName(String fullName);
    List<Staff> getAllByIdIsNotNull();
}
