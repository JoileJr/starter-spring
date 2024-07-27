package com.starter.spring.infra.persistence.jpa.pessoa;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<PessoaEntity, Long> {
    Optional<PessoaEntity> findByEmail(String email);
}
