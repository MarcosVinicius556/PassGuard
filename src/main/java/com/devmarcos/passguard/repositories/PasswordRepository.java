package com.devmarcos.passguard.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devmarcos.passguard.entities.Password;

public interface PasswordRepository extends JpaRepository<Password, Long> {
    
}
