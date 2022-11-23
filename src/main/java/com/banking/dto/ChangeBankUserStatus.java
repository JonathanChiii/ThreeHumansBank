package com.banking.dto;

import com.banking.model.ModelUtility.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChangeBankUserStatus {
    @NotEmpty(message = "User ID cannot be empty.")
    private Long id;
    @NotEmpty(message = "Desired status cannot be empty.")
    private Status status;
}
