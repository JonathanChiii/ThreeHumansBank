package com.banking.model;

import com.banking.model.ModelUtility.StringPrefixedSequenceIdGenerator;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "customer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Inheritance(strategy = InheritanceType.JOINED) // Table Per Subclass Inheritance Mapping
public class Customer implements BankUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique=true, nullable = false)
    private String username;
    @Column(nullable = false)
    private String fullName;
    @Column(nullable = false)
    private String password;
    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private Set<Account> accounts = new HashSet<>();

//    @ManyToMany(fetch = FetchType.LAZY,
//            cascade = {
//                    CascadeType.PERSIST,
//                    CascadeType.MERGE
//            })
//    @JoinTable(name = "customer_security_questions",
//            joinColumns = { @JoinColumn(name = "customer_id") },
//            inverseJoinColumns = { @JoinColumn(name = "security_question_id") })
    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    private Set<SecurityQuestion> securityQuestions = new HashSet<>();

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "beneficiarySource")
    private Set<Beneficiary> beneficiaries = new HashSet<>();

    @Column(unique=true, nullable = false)
    private String aadhaar;
    private byte[] aadhaarPicture;
    @Column(unique=true, nullable = false)
    private String PAN;
    private byte[] PANPicture;
}
