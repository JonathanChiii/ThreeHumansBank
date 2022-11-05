package com.banking.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Parameter;


import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "accounts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_seq")
    @GenericGenerator(
            name = "account_seq",
            strategy = "org.thoughts.on.java.generators.StringPrefixedSequenceIdGenerator",
            parameters = {
                    @Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
                    @Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "AC"),
                    @Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d") })
    private String id;
    private enum type {
        SB,
        CA,
    };
    private Float balance;
    private enum status {
        Enabled,
        Disabled,
    };
    Boolean approved;

    @ManyToOne(fetch = FetchType.LAZY)
    private Staff approvedBy;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "source_account")
    private Set<Transaction> transferOut = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dest_account")
    private Set<Transaction> transferIn = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private Customer owner;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModified;
}