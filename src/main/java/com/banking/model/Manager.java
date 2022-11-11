package com.banking.model;

import com.banking.model.ModelUtility.Status;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "managers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Manager extends Staff{
    //@JsonManagedReference(value = "manager-staff")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "manager")
    private Set<Staff> staff = new HashSet<>();
    public Manager(Long id, String username, String fullName, String password, Set<Role> roles, Manager manager, Status status, Set<Account> accountsApproved, Set<Beneficiary> beneficiariesApproved, Set<Staff> staff) {
        super(id, username, fullName, password, roles, manager, status, accountsApproved, beneficiariesApproved);
        this.staff = staff;
    }
}

