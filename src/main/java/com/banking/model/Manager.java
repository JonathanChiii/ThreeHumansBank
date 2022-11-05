package com.banking.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "manager")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Manager extends Staff{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "manager_seq")
    @GenericGenerator(
            name = "manager_seq",
            strategy = "org.thoughts.on.java.generators.StringPrefixedSequenceIdGenerator",
            parameters = {
                    @Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
                    @Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "AD"),
                    @Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d") })
    private String id;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "manager")
    private Set<Staff> staff = new HashSet<>();

    // ToDo
}
