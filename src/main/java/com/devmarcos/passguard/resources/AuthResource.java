package com.devmarcos.passguard.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.devmarcos.passguard.dtos.LoginDTO;
import com.devmarcos.passguard.dtos.TokenDTO;
import com.devmarcos.passguard.entities.User;
import com.devmarcos.passguard.services.TokenService;

@RestController
public class AuthResource {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping( "/login" )
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
