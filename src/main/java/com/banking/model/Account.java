package com.banking.model;


import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "accounts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    @Column(unique=true)
    private Integer number;
    private enum type {
        SB,
        CA,
    };
    private float balance;
    private enum status {
        Enabled,
        Disabled,
    };

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