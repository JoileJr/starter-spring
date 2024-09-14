package com.starter.spring.repository;

import com.starter.spring.model.ResultadoParametro;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultadoParametroRepository extends JpaRepository<ResultadoParametro, Long> {
}
