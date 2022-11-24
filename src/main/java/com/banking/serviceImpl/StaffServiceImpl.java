package com.banking.serviceImpl;

import com.banking.model.Manager;
import com.banking.model.Staff;
import com.banking.repository.StaffRepository;
import com.banking.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffServiceImpl implements StaffService {
    @Autowired
    private StaffRepository staffRepository;

    @Override
    public List<Staff> findAllStaff() {
        return staffRepository.findAll();
    }

    @Override
    public Staff findById(Long id) {
        return staffRepository.getReferenceById(id);
    }

    @Override
    public Staff findByUsername(String username) {
        return staffRepository.findStaffByUsername(username);
    }

    @Override
    public List<Staff> findByManager(Manager manager) {
        return staffRepository.findStaffByManager(manager);
    }

    @Override
    public Staff save(Staff staff) {
        return staffRepository.save(staff);
    }

    @Override
    public Staff update(Staff staff) {
        Staff s = staffRepository.getReferenceById(staff.getId());
        if(s != null){
            return staffRepository.save(staff);
        }return null;
    }
}
