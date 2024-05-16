package com.devmarcos.passguard.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.devmarcos.passguard.dtos.UserCreateDTO;
import com.devmarcos.passguard.dtos.UserUpdateDTO;
import com.devmarcos.passguard.entities.User;
import com.devmarcos.passguard.repositories.UserRepository;
import com.devmarcos.passguard.services.exceptions.DatabaseException;
import com.devmarcos.passguard.services.exceptions.ResourceNotFoundException;
import com.devmarcos.passguard.services.exceptions.UserAlreadySavedException;

@Service
public class UserService {
    
    @Autowired
    private UserRepository repository;

    public void create(UserCreateDTO newUser) {
        if(repository.findByUsername(newUser.username()) != null){
            throw new UserAlreadySavedException(newUser.username());
        }

        User user = new User.Builder()
                            .setNickName(newUser.nickname())
                            .setUsername(newUser.username())
                            .setPassword(hashPassword(newUser.pass()))
                            .build();

        repository.save(user);
    }

    private String hashPassword(String password) {
    return BCrypt.hashpw(password, BCrypt.gensalt());
}

    public User findById(Long id) {
        Optional<User> opUser = repository.findById(id);
        return opUser.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public User findByUsername(String username) {
        return repository.findByUsername(username);
    }

    public List<User> findAll() {
        List<User> users = repository.findAll();
        if(users == null || users.isEmpty())
            throw new ResourceNotFoundException("TODOS");
        return users;
    }

    public User update(Long oldUserId, UserUpdateDTO newUser) {
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

    public void updateData(User oldUser, UserUpdateDTO newUser) {
        oldUser.setUsername(newUser.username());
        oldUser.setNickName(newUser.nickname());
        if(newUser.alterarSenha())
            oldUser.setPassword(newUser.password());
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
