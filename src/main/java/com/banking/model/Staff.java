package com.banking.model;

import com.banking.model.ModelUtility.Status;
import com.banking.model.ModelUtility.StringPrefixedSequenceIdGenerator;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

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
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS) // Table Per Concrete Class Inheritance Mapping
public class Staff extends Employee{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager")
    private Manager manager;
    private Status status;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "approvedBy")
    private Set<Account> accountsApproved  = new HashSet<>();

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "approvedBy")
    private Set<Beneficiary> beneficiaries = new HashSet<>();
}
