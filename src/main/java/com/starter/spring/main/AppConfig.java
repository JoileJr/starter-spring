package com.starter.spring.main;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.starter.spring.application.EnderecoUseCase;
import com.starter.spring.application.PessoaUseCase;
import com.starter.spring.infra.persistence.jpa.endereco.EnderecoRepositoryJpaGateway;
import com.starter.spring.infra.persistence.jpa.pessoa.PessoaRepositoryJpaGateway;

@Configuration
public class AppConfig {
    
    @Bean("jpa")
    public PessoaUseCase pessoaUseCaseComJpa(PessoaRepositoryJpaGateway gateway) {
        return new PessoaUseCase(gateway);
    }

    @Bean("jpa")
    public EnderecoUseCase enderecoUseCaseComJpa(EnderecoRepositoryJpaGateway gateway) {
        return new EnderecoUseCase(gateway);
    }
}
