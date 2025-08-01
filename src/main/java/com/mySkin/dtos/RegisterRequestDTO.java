package com.mySkin.dtos;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class RegisterRequestDTO {
    private String username;
    private String name;
    private String email;
    private String password;
    private Set<CharacteristicDTO> characteristics = new HashSet<>();
} 