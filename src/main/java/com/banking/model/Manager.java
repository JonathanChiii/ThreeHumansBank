package com.banking.model;

import com.banking.model.ModelUtility.StringPrefixedSequenceIdGenerator;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "manager")
    private Set<Staff> staff = new HashSet<>();

    // ToDo
}
