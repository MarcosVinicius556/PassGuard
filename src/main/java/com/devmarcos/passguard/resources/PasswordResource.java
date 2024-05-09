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

import com.devmarcos.passguard.dtos.LoginDTO;
import com.devmarcos.passguard.dtos.PasswordCreateDTO;
import com.devmarcos.passguard.dtos.TokenDTO;
import com.devmarcos.passguard.entities.Password;
import com.devmarcos.passguard.services.PasswordService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping( value = "/passwords" )
@Tag( name = "CRUD - Senhas" )
public class PasswordResource {
    
    @Autowired
    private PasswordService service;

    @PostMapping
    @Operation( summary = "Cria um usuário com base nas informações forncecidas", method = "POST" )
    @io.swagger.v3.oas.annotations.parameters.RequestBody( 
        description = "Credenciais.", 
        required = true,
        content = @Content( schema = @Schema(implementation = LoginDTO.class) ) )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "credenciais válidas!", content = @Content( schema = @Schema(implementation = TokenDTO.class) ) ),
            @ApiResponse(responseCode = "403", description = "Credenciais inválidas!", content = @Content( schema = @Schema(implementation = Void.class) ) ),
            @ApiResponse(responseCode = "500", description = "Falha interna do servidor!", content = @Content( schema = @Schema(implementation = Void.class) ))
        })
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
