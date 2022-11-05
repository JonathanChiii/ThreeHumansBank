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
    private String Aadhaar;
    private byte[] AadhaarPicture;
    private String PAN;
    private byte[] PANPicture;
}
