package com.devmarcos.passguard.dtos;

public record UserUpdateDTO(String username, String nickname, boolean alterarSenha, String password) {
    
}
