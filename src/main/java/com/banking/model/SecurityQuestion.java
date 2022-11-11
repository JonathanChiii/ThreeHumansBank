package com.banking.model;

import com.banking.model.ModelUtility.Question;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="security_questions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SecurityQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Question question;
    @Column(nullable = false)
    private String answer;

    @JsonBackReference(value = "security_question-customer")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer")
    private Customer customer;

//    @ManyToMany(fetch = FetchType.LAZY,
//            cascade = {
//                    CascadeType.PERSIST,
//                    CascadeType.MERGE
//            },
//            mappedBy = "securityQuestions")
//    @JsonIgnore
//    //@JsonIgnore is used to ignore the logical property used in serialization and deserialization.
//    private Set<Customer> customerSet = new HashSet<>();
}