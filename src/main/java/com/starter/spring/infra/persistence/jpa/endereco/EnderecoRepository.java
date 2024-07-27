package com.starter.spring.infra.persistence.jpa.endereco;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<EnderecoEntity, Long> {
    
}
