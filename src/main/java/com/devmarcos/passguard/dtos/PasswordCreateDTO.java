package com.devmarcos.passguard.dtos;

public record PasswordCreateDTO(String name, String description, String username, String password, Long userId) { 
    
}
