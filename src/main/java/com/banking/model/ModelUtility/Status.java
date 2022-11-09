package com.banking.model.ModelUtility;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Status {
    Enabled(1),
    Disabled(0);

    private int statusCode;
}
