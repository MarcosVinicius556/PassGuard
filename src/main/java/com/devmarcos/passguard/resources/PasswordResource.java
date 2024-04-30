package com.devmarcos.passguard.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devmarcos.passguard.dtos.PasswordCreateDTO;
import com.devmarcos.passguard.entities.Password;
import com.devmarcos.passguard.services.PasswordService;


@RestController
@RequestMapping( value = "/passwords" )
public class PasswordResource {
    
    @Autowired
    private PasswordService service;

    @PostMapping
    public ResponseEntity<Void> create(PasswordCreateDTO newPass) {
        service.create(newPass);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Password> findById(@PathVariable Long id) {
        Password password = service.findById(id);
        return ResponseEntity.ok().body(password);
    }

    @GetMapping
    public ResponseEntity<List<Password>> findAll() {
        List<Password> passwords = service.findAll();
        return ResponseEntity.ok().body(passwords);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody Password pass) {
        service.update(id, pass);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
