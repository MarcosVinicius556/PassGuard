package com.devmarcos.passguard.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.devmarcos.passguard.dtos.UserCreateDTO;
import com.devmarcos.passguard.entities.User;
import com.devmarcos.passguard.repositories.UserRepository;

public class UserService {
    
    @Autowired
    private UserRepository repository;

    public void create(UserCreateDTO newUser) {
        try {

            User user = new User.Builder()
                                .setNickName(newUser.nickname())
                                .setUsername(newUser.username())
                                .setPassword(newUser.password())
                                .build();

            repository.save(user);
        } catch (Exception e) {
            // TODO: Criar exceções personalizadas com algum ExceptionHandler
            e.printStackTrace();
        }
    }

    public User findById(Long id) {
        Optional<User> opUser = repository.findById(id);
        return opUser.orElseThrow(() -> new RuntimeException()); //ExceptionHandler que tratará isto (Criar uma exceção personalizada)
    }

}
