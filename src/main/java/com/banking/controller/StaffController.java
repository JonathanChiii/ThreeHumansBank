package com.banking.controller;

import com.banking.model.ModelUtility.ERole;
import com.banking.model.Role;
import com.banking.model.ModelUtility.Status;
import com.banking.model.Staff;
import com.banking.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@CrossOrigin
@RestController
@RequestMapping("/staff")
public class StaffController {
    @Autowired
    StaffService staffService;

    @GetMapping("test1")
    public Staff test1(){
        Staff staff = new Staff(null, "Staff1", "Chris Evans", "0000", Set.of(new Role(ERole.Customer), new Role(ERole.Staff)),null, Status.Enabled, null, null);
        return staffService.save(staff);
    }

}