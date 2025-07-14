package com.mySkin.dtos;

import lombok.Data;

@Data
public class RegisterRequestDTO {
    private String username;
    private String name;
    private String email;
    private String password;
} 