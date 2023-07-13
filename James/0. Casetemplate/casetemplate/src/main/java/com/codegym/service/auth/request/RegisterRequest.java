package com.codegym.service.auth.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RegisterRequest {

    private String fullName;

    private String phoneNumber;

    private String username;

    private String password;

    private String email;

}