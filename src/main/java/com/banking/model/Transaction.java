package com.banking.model;

import com.banking.BankApplication;
import com.banking.model.ModelUtility.StringPrefixedSequenceIdGenerator;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "transactions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_seq")
    @GenericGenerator(
            name = "transaction_seq",
            strategy = "com.banking.model.ModelUtility.StringPrefixedSequenceIdGenerator",
            parameters = {
                    //@Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "0"),
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "TR_"),
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%06d") })
    private String id;

    @JsonBackReference(value = "transaction-bank_user")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bank_user")
    private BankUser bankUser;

    @JsonBackReference(value = "transaction_out-account")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sourceAccount")
    private Account sourceAccount;

    @JsonBackReference(value = "transaction_in-account")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "destAccount")
    private Account destAccount;

    private Float amount;
    private String reason;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;
}