package com.banking.dto;

import lombok.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserLogin {
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
}