package com.devmarcos.passguard.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devmarcos.passguard.dtos.LoginDTO;
import com.devmarcos.passguard.dtos.TokenDTO;
import com.devmarcos.passguard.entities.User;
import com.devmarcos.passguard.services.TokenService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/auth")
@Tag( name = "Autenticação" )
public class AuthResource {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping( value= "/login", consumes = "application/json" )
    @Operation( summary = "Gera um token com validade de 10 minutos para acesso aos demais endpoints da aplicação", method = "POST" )
    @io.swagger.v3.oas.annotations.parameters.RequestBody( 
        description = "Credenciais.", 
        required = true,
        content = @Content( schema = @Schema(implementation = LoginDTO.class) ) )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "credenciais válidas!", content = @Content( schema = @Schema(implementation = TokenDTO.class) ) ),
        @ApiResponse(responseCode = "403", description = "Credenciais inválidas!", content = @Content( schema = @Schema(implementation = Void.class) ) ),
        @ApiResponse(responseCode = "500", description = "Falha interna do servidor!", content = @Content( schema = @Schema(implementation = Void.class) ))
    })
    public ResponseEntity<TokenDTO> doLogin(@RequestBody LoginDTO loginDTO) {
        
        var usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(loginDTO.username(), loginDTO.pass());

        Authentication auth = this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        User user = (User) auth.getPrincipal();

        String token = this.tokenService.gerarToken(user);

        String expiresAt = this.tokenService.getExpiresAt(token);

        TokenDTO tokenDTO = new TokenDTO(token, expiresAt);

        return ResponseEntity.ok().body(tokenDTO);

    }
    
}
