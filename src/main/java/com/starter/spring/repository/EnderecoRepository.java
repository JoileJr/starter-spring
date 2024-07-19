package com.starter.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.starter.spring.model.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
    
}
