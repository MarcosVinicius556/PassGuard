package com.devmarcos.passguard.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devmarcos.passguard.dtos.PasswordCreateDTO;
import com.devmarcos.passguard.entities.Password;
import com.devmarcos.passguard.entities.User;
import com.devmarcos.passguard.repositories.PasswordRepository;
import com.devmarcos.passguard.repositories.UserRepository;
import com.devmarcos.passguard.services.exceptions.DatabaseException;
import com.devmarcos.passguard.services.exceptions.ResourceNotFoundException;

@Service
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
            throw new DatabaseException("Oops, ocorreu um erro ao inserir o registro. Detalhes: " + e.getMessage());
        }
    }

    public Password findById(Long id) {
        Optional<Password> opPass = repository.findById(id);
        return opPass.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public List<Password> findAll() {
        List<Password> passwords = repository.findAll();
        if(passwords == null || passwords.isEmpty())
            throw new ResourceNotFoundException("TODOS");
        return passwords;
    }

    public Password update(Long oldPassId, Password newPass) {
        try {
            
            Password oldPass = repository.getReferenceById(oldPassId);
            if(oldPass == null)
                throw new ResourceNotFoundException(oldPassId);
            updateData(oldPass, newPass);
            return repository.save(oldPass);
        } catch (Exception e) {
            throw new DatabaseException("Oops, ocorreu um erro ao atualizar o registro. Detalhes: " + e.getMessage());
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
                throw new ResourceNotFoundException(id);

            repository.delete(passToRemove);
        } catch (Exception e) {
            throw new DatabaseException("Oops, ocorreu um erro ao remover o registro. Detalhes: " + e.getMessage());
        }
    }

    public Page<Password> findByUserId(Long userId, Pageable pageable) {
        return repository.findByUserId(userId, pageable);
    }

}
