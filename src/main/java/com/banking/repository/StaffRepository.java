package com.banking.repository;

import com.banking.model.Manager;
import com.banking.model.ModelUtility.Status;
import com.banking.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StaffRepository extends JpaRepository<Staff, Long> {
    Staff findStaffByUsername(String username);
    List<Staff> findStaffByFullName(String fullName);
    List<Staff> findStaffByManager(Manager manager);
    List<Staff> findStaffByStatusIs(Status status);
}
