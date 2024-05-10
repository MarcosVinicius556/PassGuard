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

import com.devmarcos.passguard.dtos.UserCreateDTO;
import com.devmarcos.passguard.entities.User;
import com.devmarcos.passguard.resources.exceptions.StandardError;
import com.devmarcos.passguard.services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping( value = "/users" )
@Tag( name = "CRUD - Usuário" )
public class UserResource {
    
    @Autowired
    private UserService service;

    @PostMapping
    @Operation( summary = "Insere o registro de um usuário com base nas informações forncecidas", method = "POST" )
    @io.swagger.v3.oas.annotations.parameters.RequestBody( 
        description = "Informações para criação do usuário.", 
        required = true,
        content = @Content( schema = @Schema(implementation = UserCreateDTO.class) ) )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Requisição OK! Usuário criada com sucesso!", content = @Content( schema = @Schema(implementation = Void.class) ) ),
            @ApiResponse(responseCode = "400", description = "Ocorreu um erro ao efetuar a operação no banco de dados!", content = @Content( schema = @Schema(implementation = StandardError.class) ) ),
            @ApiResponse(responseCode = "403", description = "Acesso negado!", content = @Content( schema = @Schema(implementation = Void.class) ) ),
            @ApiResponse(responseCode = "500", description = "Falha interna do servidor!", content = @Content( schema = @Schema(implementation = StandardError.class) ))
        })
    public ResponseEntity<Void> create(@RequestBody UserCreateDTO newUser) {
        service.create(newUser);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @Operation( summary = "Busca um usuário com o ID forncecido", method = "GET" )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Requisição OK! Registro encontrado", content = @Content( schema = @Schema(implementation = User.class) ) ),
        @ApiResponse(responseCode = "403", description = "Acesso negado!", content = @Content( schema = @Schema(implementation = Void.class) ) ),
        @ApiResponse(responseCode = "404", description = "Nenhum Registro encontrado!", content = @Content( schema = @Schema(implementation = StandardError.class) ) ),
        @ApiResponse(responseCode = "500", description = "Falha interna do servidor!", content = @Content( schema = @Schema(implementation = StandardError.class) ) )
    })
    public ResponseEntity<User> findById(@PathVariable Long id) {
        User user = service.findById(id);
        return ResponseEntity.ok().body(user);
    }

    @GetMapping
    @Operation( summary = "Busca todos os usuário inseridos no banco", method = "GET" )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Requisição OK! Registros encontrados", content = @Content( schema = @Schema(implementation = User.class) ) ),
        @ApiResponse(responseCode = "403", description = "Acesso negado!", content = @Content( schema = @Schema(implementation = Void.class) ) ),
        @ApiResponse(responseCode = "404", description = "Nenhum Registro encontrado!", content = @Content( schema = @Schema(implementation = StandardError.class) ) ),
        @ApiResponse(responseCode = "500", description = "Falha interna do servidor!", content = @Content( schema = @Schema(implementation = StandardError.class) ) )
    })
    public ResponseEntity<List<User>> findAll() {
        List<User> users = service.findAll();
        return ResponseEntity.ok().body(users);
    }

    @PutMapping("/{id}")
    @Operation( summary = "Atualiza o usuário no banco", method = "PUT" )
    @io.swagger.v3.oas.annotations.parameters.RequestBody( 
        description = "Informações do usuário para serem atualizados.", 
        required = true,
        content = @Content( schema = @Schema(implementation = UserCreateDTO.class) ) )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Requisição OK! Registro atualizado", content = @Content( schema = @Schema(implementation = Void.class) ) ),
        @ApiResponse(responseCode = "403", description = "Acesso negado!", content = @Content( schema = @Schema(implementation = Void.class) ) ),
        @ApiResponse(responseCode = "400", description = "Ocorreu um erro ao efetuar a operação no banco de dados!", content = @Content( schema = @Schema(implementation = StandardError.class) ) ),
        @ApiResponse(responseCode = "404", description = "Nenhum Registro encontrado!", content = @Content( schema = @Schema(implementation = StandardError.class) ) ),
        @ApiResponse(responseCode = "500", description = "Falha interna do servidor!", content = @Content( schema = @Schema(implementation = StandardError.class) ) )
    })
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody User user) {
        service.update(id, user);
        return ResponseEntity.noContent().build();
    }

    @Operation( summary = "Atualiza a senha no banco no banco", method = "DELETE" )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Requisição OK! Registro removido", content = @Content( schema = @Schema(implementation = Void.class) ) ),
        @ApiResponse(responseCode = "403", description = "Acesso negado!", content = @Content( schema = @Schema(implementation = Void.class) ) ),
        @ApiResponse(responseCode = "400", description = "Ocorreu um erro ao efetuar a operação no banco de dados!", content = @Content( schema = @Schema(implementation = StandardError.class) ) ),
        @ApiResponse(responseCode = "404", description = "Nenhum Registro encontrado!", content = @Content( schema = @Schema(implementation = StandardError.class) ) ),
        @ApiResponse(responseCode = "500", description = "Falha interna do servidor!", content = @Content( schema = @Schema(implementation = StandardError.class) ) )
    })
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
