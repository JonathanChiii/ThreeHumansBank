package com.example.bank.model;

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
    private Integer id;
    @Column(unique=true)
    private String username;
    private String fullName;
    private String phone;
    private String password;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "accounts", fetch = FetchType.LAZY)
    private Set<Account> accounts = new HashSet<>();
    //@OneToMany(cascade = CascadeType.ALL, mappedBy = "questions", fetch = FetchType.LAZY)
    //private Set<SecretQuestion> secretQuestions = new HashSet<>();
    // Need to re-think this
    private String aadhar;
    private String aadharUrl;
    private String pan;
    private String panUrl;
}
