package com.devmarcos.passguard.services.exceptions;

public class UserAlreadySavedException extends RuntimeException {
    
    public UserAlreadySavedException(String username){
        super("O usuário " + username + " já foi utilizado. Tente outro nome de usuário");
    }

}
