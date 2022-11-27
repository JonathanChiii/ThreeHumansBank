package com.banking.dto.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse {
    private String AccessToken;
    private Long id;
    private String username;
    private String fullName;
    private List<String> roles;

}
