package com.starter.spring.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.starter.spring.service.config.ConfigService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
public class DbConfig {

    private final ConfigService configService;
    
    @Bean
    public CommandLineRunner instanciaDB() {
        return args -> {
            if (!configService.perfisInstanciados()) {
                configService.instanciaPerfil();
            }
        };
    }
}
