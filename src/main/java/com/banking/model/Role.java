package com.banking.model;


import com.banking.model.ModelUtility.ERole;
import com.banking.model.ModelUtility.StringPrefixedSequenceIdGenerator;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
@Entity
@Table(name = "roles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_seq")
    @GenericGenerator(
            name = "role_seq",
            strategy = "com.banking.model.ModelUtility.StringPrefixedSequenceIdGenerator",
            parameters = {
                    //@Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "0"),
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "ro_"),
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%01d") })
    private String id;
    @Enumerated(EnumType.STRING)
    // Declared that its value should be converted from what is effectively a String in the database to the ERole type.
    // we can safely add new enum values or change our enum's order. However, renaming an enum value will still break the database data.
    @Column(length = 20)
    private ERole name;
    public Role(ERole name) {
        this.name = name;
    }
}

