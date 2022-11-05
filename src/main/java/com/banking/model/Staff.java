package com.banking.model;

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
public class Staff extends Employee{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "staff_seq")
    @GenericGenerator(
            name = "staff_seq",
            strategy = "org.thoughts.on.java.generators.StringPrefixedSequenceIdGenerator",
            parameters = {
                    @Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
                    @Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "ST"),
                    @Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d") })
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Admin admin;
    private enum status{
        Enabled,
        Disabled,
    }
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "approved_by")
    private Set<Transaction> transactions = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "approved_by")
    private Set<Beneficiary> beneficiaries = new HashSet<>();

    // ToDo
}
