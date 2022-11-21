package com.banking.controller;

import com.banking.model.Customer;
import com.banking.model.ModelUtility.ERole;
import com.banking.model.Role;
import com.banking.model.ModelUtility.Status;
import com.banking.model.Staff;
import com.banking.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@CrossOrigin
@RestController
@RequestMapping("/staff")
public class StaffController {
    @Autowired
    StaffService staffService;

    @GetMapping("test1")
    public Staff test1(){
        Staff staff = new Staff(null, "CaptainAmerica", "Chris Evans", "0000", Status.Enabled, Set.of(new Role(ERole.Customer), new Role(ERole.Staff)),null, null, null);
        return staffService.save(staff);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Staff register(@RequestBody Staff staff){
        //ToDo
        return staffService.save(staff);
    }

}
