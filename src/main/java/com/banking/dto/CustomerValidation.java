package com.banking.dto;

import com.banking.model.Customer;
import com.banking.model.SecurityQuestion;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
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
    private Set<Customer> beneficiaries = new HashSet<>();
    @NotEmpty
    private String aadhar;
    @NotEmpty
    private byte[] aadharPicture;
    @NotEmpty
    private String pan;
    private byte[] panPicture;
}
