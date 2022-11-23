package com.banking.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table (name = "beneficiaries")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Beneficiary extends Customer{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonBackReference(value = "beneficiary-customer")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "beneficiarySource")
    private Customer beneficiarySource;

    //Unidirectional
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "primary_account", referencedColumnName = "id")
    private Account primaryAccount;

    Boolean isApproved;

    @JsonBackReference(value = "beneficiary-staff")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "approvedBy")
    private Staff approvedBy;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
}
