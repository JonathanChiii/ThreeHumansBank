package com.banking.model;

import com.banking.model.ModelUtility.Status;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "staff")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Inheritance(strategy = InheritanceType.JOINED) // Table Per subclass Inheritance Mapping
public class Staff extends BankUser {
    @JsonBackReference(value = "staff-manager")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager")
    private Manager manager;

    //@JsonManagedReference(value = "staff-account")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "approvedBy")
    private Set<Account> accountsApproved  = new HashSet<>();

    //@JsonManagedReference(value = "staff-beneficiary")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "approvedBy")
    private Set<Beneficiary> beneficiariesApproved = new HashSet<>();

    public Staff(Long id, String username, String fullName, String password, Status status, Set<Transaction> transactions, Set<Role> roles, Manager manager, Set<Account> accountsApproved, Set<Beneficiary> beneficiariesApproved) {
        super(id, username, fullName, password, status, transactions, roles);
        this.manager = manager;
        this.accountsApproved = accountsApproved;
        this.beneficiariesApproved = beneficiariesApproved;
    }

    public Staff(String username, String fullName, String password, Set<Role> roles){
        super(username, fullName, password, roles);
    }
}
