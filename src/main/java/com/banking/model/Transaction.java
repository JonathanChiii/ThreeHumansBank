package com.banking.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

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