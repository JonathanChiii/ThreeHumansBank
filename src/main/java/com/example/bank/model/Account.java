package com.example.bank.model;


import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
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

    @ManyToOne(cascade = CascadeType.ALL)
    private Customer owner;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCreated;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModified;
}