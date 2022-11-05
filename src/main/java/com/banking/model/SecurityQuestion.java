package com.banking.model;

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
    @GeneratedValue
    private Long id;
    private String question;
    private String answer;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "securityQuestions")
    @JsonIgnore
    //@JsonIgnore is used to ignore the logical property used in serialization and deserialization.
    private Set<Customer> customerSet = new HashSet<>();
}