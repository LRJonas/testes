package com.example.testes.config;

import com.example.testes.model.User;
import com.example.testes.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
@Profile("local")
public class LocalConfig {

    @Autowired
    UserRepository userRepository;

    @Bean
    public List<User> startDB(){
        User u1 = new User(122L, "Cuca Belludo", "cuca@beludo.com", "123");
        User u2 = new User(123L, "Maria", "maria@email.com", "456");

        userRepository.save(u1);
        userRepository.save(u2);

        return List.of(u1, u2);
    }
}
