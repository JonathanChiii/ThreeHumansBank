package com.banking.dto;

import com.banking.model.ModelUtility.AccountType;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AccountValidation {
    @NotEmpty(message = "Must Select account type.")
    private AccountType type;

    @Min(0)
    private Float balance;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModified;
}

