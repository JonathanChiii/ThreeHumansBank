package com.banking.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
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
@Inheritance(strategy = InheritanceType.JOINED) // Table Per Subclass Inheritance Mapping
public class Customer implements BankUser {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_seq")
    @GenericGenerator(
            name = "customer_seq",
            strategy = "org.thoughts.on.java.generators.StringPrefixedSequenceIdGenerator",
            parameters = {
                    @Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
                    @Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "CX"),
                    @Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d") })
    private String id;
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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "beneficiarySource")
    private Set<Beneficiary> beneficiaries = new HashSet<>();

    @Column(unique=true)
    private String Aadhaar;
    private byte[] AadhaarPicture;
    @Column(unique=true)
    private String PAN;
    private byte[] PANPicture;
}
