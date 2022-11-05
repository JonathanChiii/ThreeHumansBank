package com.banking.dto;

import com.banking.model.SecurityQuestion;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CustomerValidation {
    @NotEmpty
    private String username;
    @NotEmpty
    private String fullName;
    @NotEmpty
    private String password;
    private Set<SecurityQuestion> securityQuestions;
    @NotEmpty
    private String Aadhaar;
    @NotEmpty
    private byte[] AadhaarPicture;
    @NotEmpty
    private String PAN;
    private byte[] PANPicture;
}
