package com.banking.repository;

import com.banking.model.Manager;
import com.banking.model.ModelUtility.Status;
import com.banking.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {
    Optional<Staff> findStaffByUsername(String username);
    List<Staff> findStaffByFullName(String fullName);
    List<Staff> findStaffByManager(Manager manager);
    List<Staff> findStaffByStatus(Status status);
}
