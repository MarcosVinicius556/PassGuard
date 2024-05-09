package com.devmarcos.passguard.config.docs;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class OpenAPIConfiguration {
    
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                    .info(new Info()
                             .title("PassGuard")
                             .version("1.0"))
                    .components(new Components()
                                    .addSecuritySchemes("Token de Acesso", new SecurityScheme()
                                                                      .type(SecurityScheme.Type.HTTP)
                                                                      .scheme("bearer")
                                                                      .description("Token para acesso da aplicação (Pode ser gerado na rota '/auth/login')")
                                                                      .bearerFormat("JWT")
                                                                      .in(SecurityScheme.In.HEADER)
                                                                      .name("Authorization")))
                    .addSecurityItem(new SecurityRequirement()
                                        .addList("bearer-jwt", Arrays.asList("read", "write")));
    }

}
