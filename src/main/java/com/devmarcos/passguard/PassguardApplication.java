package com.devmarcos.passguard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//Configurando documentação da API
// @OpenAPIDefinition(info = @Info( title = "PassGuard", version = "1", description = "API desenvolvida para guardar usuários, senhas e observações de apps diversos" ), security = @SecurityRequirement(name = "Bearer Token"))
public class PassguardApplication {

	public static void main(String[] args) {
		SpringApplication.run(PassguardApplication.class, args); 
	}

}
