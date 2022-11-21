package com.banking.model;


import com.banking.model.ModelUtility.ERole;
import lombok.*;

import javax.persistence.*;
@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Role{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Enumerated(EnumType.STRING)
    // Declared that its value should be converted from what is effectively a String in the database to the ERole type.
    // we can safely add new enum values or change our enum's order. However, renaming an enum value will still break the database data.
    @Column(length = 20)
    private ERole name;
    public Role(ERole name) {
        this.name = name;
    }
}

