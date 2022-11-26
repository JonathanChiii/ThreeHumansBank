package com.banking.dto;

import com.banking.model.ModelUtility.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;

@AllArgsConstructor
@Getter
public class ChangeStatus {
    private Status status;
}
