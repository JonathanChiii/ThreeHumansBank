package com.banking.model;

import com.banking.model.ModelUtility.AccountType;
import com.banking.model.ModelUtility.Status;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @GenericGenerator(
//            name = "account_seq",
//            strategy = "org.thoughts.on.java.generators.StringPrefixedSequenceIdGenerator",
//            parameters = {
//                    @Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
//                    @Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "AC"),
//                    @Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d") })
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private AccountType type;

    private Float balance;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Status status;

    Boolean approved;

    @JsonBackReference(value = "account-owner")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner")
    private Customer owner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "approvedBy")
    private Staff approvedBy;

    //@JsonManagedReference(value = "account-transaction_out")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sourceAccount")
    private Set<Transaction> transferOut = new HashSet<>();

    //@JsonManagedReference(value = "account-transaction_in")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "destAccount")
    private Set<Transaction> transferIn = new HashSet<>();

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModified;
}