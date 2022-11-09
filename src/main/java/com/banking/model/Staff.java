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

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager")
    private Manager manager;
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Status status;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "approvedBy")
    private Set<Account> accountsApproved  = new HashSet<>();

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "approvedBy")
    private Set<Beneficiary> beneficiariesApproved = new HashSet<>();

    public Staff(Long id, String username, String fullName, String password, Set<Role> roles, Manager manager, Status status, Set<Account> accountsApproved, Set<Beneficiary> beneficiariesApproved) {
        super(id, username, fullName, password, roles);
        this.manager = manager;
        this.status = status;
        this.accountsApproved = accountsApproved;
        this.beneficiariesApproved = beneficiariesApproved;
    }
}
