package com.devmarcos.passguard.services;

import java.util.List;
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

    public List<User> findAll() {
        List<User> users = repository.findAll();
        if(users == null || users.isEmpty())
            throw new RuntimeException(); //TODO substituir por uma exceção personalizada
        return users;
    }

    public User update(Long oldUserId, User newUser) {
        try {
            
            User oldUser = repository.getReferenceById(oldUserId);
            if(oldUser == null)
                throw new RuntimeException();
            updateData(oldUser, newUser);
            return repository.save(oldUser);
        } catch (Exception e) {
            // TODO: Criar exceptionHandler
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public void updateData(User oldUser, User newUser) {
        oldUser.setUsername(newUser.getUsername()); 
        oldUser.setPassword(newUser.getPassword());
        oldUser.setNickName(newUser.getNickName());
    }

    public void delete(Long id) {        
        try {
            User userToRemove = repository.getReferenceById(id);
            if(userToRemove == null)
                throw new RuntimeException();

            repository.delete(userToRemove);
        } catch (Exception e) {
            // TODO: Criar exceção personalizada
            e.printStackTrace();
        }
    }

}
