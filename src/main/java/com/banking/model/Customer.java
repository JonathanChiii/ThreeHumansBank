package com.banking.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "customers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique=true)
    private String username;
    private String fullName;
    private String password;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private Set<Account> accounts = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "customer_security_questions",
            joinColumns = { @JoinColumn(name = "customer_id") },
            inverseJoinColumns = { @JoinColumn(name = "security_question_id") })
    private Set<SecurityQuestion> securityQuestions = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "beneficiary_source")
    private Set<Customer> beneficiaries = new HashSet<>();
    @ManyToOne(fetch = FetchType.LAZY)
    private Customer beneficiary_source;

    private String aadhar;
    private byte[] aadharPicture;
    private String pan;
    private byte[] panPicture;
}
