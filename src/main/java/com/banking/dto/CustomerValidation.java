package com.banking.dto;

import com.banking.model.SecurityQuestion;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CustomerValidation {
    @NotEmpty(message = "User name cannot be empty.")
    private String username;
    @NotEmpty(message = "Full name cannot be empty.")
    private String fullName;
    @NotEmpty(message = "Password cannot be empty.")
    private String password;
    private Set<SecurityQuestion> securityQuestions;
    private String Aadhaar;
    private byte[] AadhaarPicture;
    private String PAN;
    private byte[] PANPicture;
}
