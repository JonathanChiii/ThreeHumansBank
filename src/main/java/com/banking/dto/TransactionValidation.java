package com.banking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionValidation {
    @NotEmpty(message = "Source account number cannot be empty.")
    private String sourceAccountNumber;
    @NotEmpty(message = "Destination account number cannot be empty.")
    private String destAccountNumber;
    @Min(0)
    private Float amount;
    private String reason;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;
}
