package com.banking.model;

import com.banking.model.ModelUtility.ERole;
import com.banking.model.ModelUtility.Status;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS) // Table Per Concrete Class Inheritance Mapping
@Inheritance(strategy = InheritanceType.JOINED) // Table Per Subclass Inheritance Mapping
public class BankUser implements Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false)
    private String fullName;
    @Column(nullable = false)
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Status status;

    //@JsonManagedReference(value = "bank_user-transaction")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bankUser")
    private Set<Transaction> transactions;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "user_roles",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "role_id") })
    @NotEmpty
    private Set<Role> roles = new HashSet<>();

    public BankUser(String username, String fullName, String password, Set<Role> roles) {
        this.username = username;
        this.fullName = fullName;
        this.password = password;
        this.roles = roles;
        this.status = Status.Enabled;
    }
}
