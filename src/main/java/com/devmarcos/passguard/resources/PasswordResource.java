package com.devmarcos.passguard.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devmarcos.passguard.dtos.PasswordCreateDTO;
import com.devmarcos.passguard.entities.Password;
import com.devmarcos.passguard.resources.exceptions.StandardError;
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
    @Operation( summary = "Insere o registro de uma senha com base nas informações forncecidas", method = "POST" )
    @io.swagger.v3.oas.annotations.parameters.RequestBody( 
        description = "Informações para criação da senha.", 
        required = true,
        content = @Content( schema = @Schema(implementation = PasswordCreateDTO.class) ) )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Requisição OK! Senha criada com sucesso!", content = @Content( schema = @Schema(implementation = Void.class) ) ),
            @ApiResponse(responseCode = "400", description = "Ocorreu um erro ao efetuar a operação no banco de dados!", content = @Content( schema = @Schema(implementation = StandardError.class) ) ),
            @ApiResponse(responseCode = "403", description = "Acesso negado!", content = @Content( schema = @Schema(implementation = Void.class) ) ),
            @ApiResponse(responseCode = "500", description = "Falha interna do servidor!", content = @Content( schema = @Schema(implementation = StandardError.class) ))
        })
    public ResponseEntity<Void> create(@RequestBody PasswordCreateDTO newPass) {
        service.create(newPass);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @Operation( summary = "Busca uma senha com o ID forncecido", method = "GET" )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Requisição OK! Registro encontrado", content = @Content( schema = @Schema(implementation = Password.class) ) ),
        @ApiResponse(responseCode = "403", description = "Acesso negado!", content = @Content( schema = @Schema(implementation = Void.class) ) ),
        @ApiResponse(responseCode = "404", description = "Nenhum Registro encontrado!", content = @Content( schema = @Schema(implementation = StandardError.class) ) ),
        @ApiResponse(responseCode = "500", description = "Falha interna do servidor!", content = @Content( schema = @Schema(implementation = StandardError.class) ) )
    })
    public ResponseEntity<Password> findById(@PathVariable Long id) {
        Password password = service.findById(id);
        return ResponseEntity.ok().body(password);
    }

    @GetMapping
    @Operation( summary = "Busca todas as senha inseridas no banco", method = "GET" )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Requisição OK! Registros encontrados", content = @Content( schema = @Schema(implementation = Password.class) ) ),
        @ApiResponse(responseCode = "403", description = "Acesso negado!", content = @Content( schema = @Schema(implementation = Void.class) ) ),
        @ApiResponse(responseCode = "404", description = "Nenhum Registro encontrado!", content = @Content( schema = @Schema(implementation = StandardError.class) ) ),
        @ApiResponse(responseCode = "500", description = "Falha interna do servidor!", content = @Content( schema = @Schema(implementation = StandardError.class) ) )
    })
    public ResponseEntity<List<Password>> findAll() {
        List<Password> passwords = service.findAll();
        return ResponseEntity.ok().body(passwords);
    }

    @PutMapping("/{id}")
    @Operation( summary = "Atualiza a senha no banco", method = "PUT" )
    @io.swagger.v3.oas.annotations.parameters.RequestBody( 
        description = "Informações da senha para serem atualizadas.", 
        required = true,
        content = @Content( schema = @Schema(implementation = PasswordCreateDTO.class) ) )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Requisição OK! Registro atualizado", content = @Content( schema = @Schema(implementation = Void.class) ) ),
        @ApiResponse(responseCode = "403", description = "Acesso negado!", content = @Content( schema = @Schema(implementation = Void.class) ) ),
        @ApiResponse(responseCode = "400", description = "Ocorreu um erro ao efetuar a operação no banco de dados!", content = @Content( schema = @Schema(implementation = StandardError.class) ) ),
        @ApiResponse(responseCode = "404", description = "Nenhum Registro encontrado!", content = @Content( schema = @Schema(implementation = StandardError.class) ) ),
        @ApiResponse(responseCode = "500", description = "Falha interna do servidor!", content = @Content( schema = @Schema(implementation = StandardError.class) ) )
    })
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody Password pass) {
        service.update(id, pass);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @Operation( summary = "Deleta a senha no banco", method = "DELETE" )
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

    @GetMapping("/byUser/{id}")
    @Operation( summary = "Busca as senhas salvas no banco com base no ID informado", method = "GET" )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Requisição OK! Registro removido", content = @Content( schema = @Schema(implementation = Page.class) ) ),
        @ApiResponse(responseCode = "403", description = "Acesso negado!", content = @Content( schema = @Schema(implementation = Void.class) ) ),
        @ApiResponse(responseCode = "400", description = "Ocorreu um erro ao efetuar a operação no banco de dados!", content = @Content( schema = @Schema(implementation = StandardError.class) ) ),
        @ApiResponse(responseCode = "404", description = "Nenhum Registro encontrado!", content = @Content( schema = @Schema(implementation = StandardError.class) ) ),
        @ApiResponse(responseCode = "500", description = "Falha interna do servidor!", content = @Content( schema = @Schema(implementation = StandardError.class) ) )
    })
    public ResponseEntity<Page<Password>> findByUser(@PathVariable Long id, Pageable pageable) {
        return ResponseEntity.ok().body(service.findByUserId(id, pageable));
    }

}
