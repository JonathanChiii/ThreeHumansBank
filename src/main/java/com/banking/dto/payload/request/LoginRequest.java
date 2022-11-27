package com.banking.dto.payload.request;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LoginRequest {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}