package com.devmarcos.passguard.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.devmarcos.passguard.dtos.PasswordCreateDTO;
import com.devmarcos.passguard.entities.Password;
import com.devmarcos.passguard.entities.User;
import com.devmarcos.passguard.repositories.PasswordRepository;
import com.devmarcos.passguard.repositories.UserRepository;

public class PasswordService {

    @Autowired
    private PasswordRepository repository;

    @Autowired
    private UserRepository userRepository;

    public void create(PasswordCreateDTO newPassword) {
        try {
            User user = userRepository.getReferenceById(newPassword.userId());
            Password pass = new Password.Builder()
                                .setName(newPassword.name())
                                .setDescription(newPassword.description())
                                .setUsername(newPassword.username())
                                .setPassword(newPassword.password())
                                .setUser(user)
                                .build();

            repository.save(pass);
        } catch (Exception e) {
            // TODO: Criar exceções personalizadas com algum ExceptionHandler
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public Password findById(Long id) {
        Optional<Password> opPass = repository.findById(id);
        return opPass.orElseThrow(() -> new RuntimeException()); //ExceptionHandler que tratará isto (Criar uma exceção personalizada)
    }

    public List<Password> findAll() {
        List<Password> passwords = repository.findAll();
        if(passwords == null || passwords.isEmpty())
            throw new RuntimeException(); //TODO substituir por uma exceção personalizada
        return passwords;
    }

    public Password update(Long oldPassId, Password newPass) {
        try {
            
            Password oldPass = repository.getReferenceById(oldPassId);
            if(oldPass == null)
                throw new RuntimeException();
            updateData(oldPass, newPass);
            return repository.save(oldPass);
        } catch (Exception e) {
            // TODO: Criar exceptionHandler
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public void updateData(Password oldPass, Password newPass) {
        oldPass.setName(newPass.getName());
        oldPass.setDescription(newPass.getDescription());
        oldPass.setUsername(newPass.getUsername()); 
        oldPass.setPassword(newPass.getPassword());
    }

    public void delete(Long id) {        
        try {
            Password passToRemove = repository.getReferenceById(id);
            if(passToRemove == null)
                throw new RuntimeException();

            repository.delete(passToRemove);
        } catch (Exception e) {
            // TODO: Criar exceção personalizada
            e.printStackTrace();
        }
    }

}
