package com.devmarcos.passguard.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devmarcos.passguard.entities.Password;

public interface PasswordRepository extends JpaRepository<Password, Long> {
    
    @Query("FROM Password WHERE user.id = :userId ORDER BY name")
    Page<Password> findByUserId(long userId, Pageable pageable);

}
