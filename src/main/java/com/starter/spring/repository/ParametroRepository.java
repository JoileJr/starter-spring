package com.starter.spring.repository;

import com.starter.spring.model.Parametro;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParametroRepository extends JpaRepository<Parametro, Long> {
    List<Parametro> findByTipoExame_Id(Long tipoExameId);
}
