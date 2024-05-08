package com.devmarcos.passguard.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devmarcos.passguard.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
    public User findByUsername(String username);

}
