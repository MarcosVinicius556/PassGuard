package com.devmarcos.passguard.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.devmarcos.passguard.entities.Password;
import com.devmarcos.passguard.entities.User;
import com.devmarcos.passguard.repositories.PasswordRepository;
import com.devmarcos.passguard.repositories.UserRepository;

@Configuration
@Profile("dev")
public class InitialTestSeeding implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordRepository passRepository;

    @Override
    public void run(String... args) throws Exception {

        User u_1 = new User.Builder()
                           .setNickName("marcos_123")
                           .setUsername("marcos")
                           .setPassword("$2a$12$k5T9AKAzIlQOUanIYrfdjeTdAcB3MW6ybi5JLyijtQqvXqqfwcNli") //123 - bcrypt
                           .build();

        User u_2 = new User.Builder()
                           .setNickName("ellen_123")
                           .setUsername("ellen")
                           .setPassword("$2a$12$k5T9AKAzIlQOUanIYrfdjeTdAcB3MW6ybi5JLyijtQqvXqqfwcNli") //123 - bcrypt
                           .build();

        User u_3 = new User.Builder()
                           .setNickName("marco_123")
                           .setUsername("marco")
                           .setPassword("$2a$12$k5T9AKAzIlQOUanIYrfdjeTdAcB3MW6ybi5JLyijtQqvXqqfwcNli") //123 - bcrypt
                           .build();

        Password pw_1 = new Password.Builder()
                                    .setName("Senha do Banco")
                                    .setUsername("")
                                    .setPassword("123")
                                    .setDescription("Senha utilizada para acessar o app do banco XXX")
                                    .setUser(u_1)
                                    .build();

        Password pw_2 = new Password.Builder()
                                    .setName("Senha do app X")
                                    .setUsername("USUARIO_TESTE")
                                    .setPassword("123")
                                    .setDescription("Senha utilizada para acessar o app X")
                                    .setUser(u_1)
                                    .build();

        Password pw_3 = new Password.Builder()
                                    .setName("Senha do Trello")
                                    .setUsername("Marcos_Vinicius")
                                    .setPassword("123")
                                    .setDescription("Senha de acesso para o site do Trello")
                                    .setUser(u_1)
                                    .build();

        Password pw_4 = new Password.Builder()
                                    .setName("Senha do Banco")
                                    .setUsername("")
                                    .setPassword("123")
                                    .setDescription("Senha utilizada para acessar o app do banco XXX")
                                    .setUser(u_2)
                                    .build();

        Password pw_5 = new Password.Builder()
                                    .setName("Senha do app X")
                                    .setUsername("USUARIO_TESTE")
                                    .setPassword("123")
                                    .setDescription("Senha utilizada para acessar o app X")
                                    .setUser(u_2)
                                    .build();
                        
        Password pw_6 = new Password.Builder()
                                    .setName("Senha do sistema da empresa X")
                                    .setUsername("meuUsuario")
                                    .setPassword("123")
                                    .setDescription("Senha utilizada para acessar o sistema de finanças da empresa")
                                    .setUser(u_2)
                                    .build();
                        
        Password pw_7 = new Password.Builder()
                                    .setName("Senha do cartão de crédito")
                                    .setUsername("")
                                    .setPassword("0000")
                                    .setDescription("Senha para o cartão x")
                                    .setUser(u_3)
                                    .build();

        /**
         * Persistindo todos os objetos no banco
         */

        userRepository.saveAll(List.of(u_1, u_2, u_3));
        passRepository.saveAll(List.of(pw_1, pw_2, pw_3, pw_4, pw_5, pw_6, pw_7));

    }
    
}
