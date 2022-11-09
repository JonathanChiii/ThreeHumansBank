package com.banking.serviceImpl;

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
    public List<Staff> getAllStaff() {
        return staffRepository.getAllByIdIsNotNull();
    }

    @Override
    public Staff getById(Long id) {
        return staffRepository.getReferenceById(id);
    }

    @Override
    public Staff getByUsername(String username) {
        return staffRepository.getStaffByUsername(username);
    }

    @Override
    public Staff save(Staff staff) {
        return staffRepository.save(staff);
    }

    @Override
    public Staff update(Staff staff) {
        Staff s = staffRepository.getReferenceById(staff.getId());
        if(s != null){
            staffRepository.save(staff);
            return staff;
        }else return null;
    }

    @Override
    public void delete(Staff staff) {
        staffRepository.delete(staff);
    }
}
