package com.starter.spring.repository;

import com.starter.spring.model.Laboratorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LaboratorioRepository extends JpaRepository<Laboratorio, Long> {
    Optional<Laboratorio> findByCnpj(String cnpj);
}
