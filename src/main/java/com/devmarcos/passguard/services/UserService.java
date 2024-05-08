package com.devmarcos.passguard.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devmarcos.passguard.dtos.UserCreateDTO;
import com.devmarcos.passguard.entities.User;
import com.devmarcos.passguard.repositories.UserRepository;
import com.devmarcos.passguard.services.exceptions.DatabaseException;
import com.devmarcos.passguard.services.exceptions.ResourceNotFoundException;

@Service
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
            throw new DatabaseException("Oops, ocorreu um erro ao inserir o registro. Detalhes: " + e.getMessage());
        }
    }

    public User findById(Long id) {
        Optional<User> opUser = repository.findById(id);
        return opUser.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public List<User> findAll() {
        List<User> users = repository.findAll();
        if(users == null || users.isEmpty())
            throw new ResourceNotFoundException("TODOS");
        return users;
    }

    public User update(Long oldUserId, User newUser) {
        try {
            
            User oldUser = repository.getReferenceById(oldUserId);
            if(oldUser == null)
                throw new ResourceNotFoundException(oldUserId);
            updateData(oldUser, newUser);
            return repository.save(oldUser);
        } catch (Exception e) {
            throw new DatabaseException("Oops, ocorreu um erro ao atualizar o registro. Detalhes: " + e.getMessage());
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
                throw new ResourceNotFoundException(id);

            repository.delete(userToRemove);
        } catch (Exception e) {
            throw new DatabaseException("Oops, ocorreu um erro ao remover o registro. Detalhes: " + e.getMessage());
        }
    }

}
