package com.banking.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "beneficiary")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Beneficiary extends Customer{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Customer beneficiary_source;

    Boolean isApproved;

    @ManyToOne(fetch = FetchType.LAZY)
    private Staff approvedBy;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
}
