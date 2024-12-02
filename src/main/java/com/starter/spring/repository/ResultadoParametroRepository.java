package com.starter.spring.repository;

import com.starter.spring.model.ResultadoParametro;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultadoParametroRepository extends JpaRepository<ResultadoParametro, Long> {
    List<ResultadoParametro> findByExame_IdAndParametro_TipoExame_Id(Long exameId, Long tipoExameId);
    List<ResultadoParametro> findByExame_Id(Long exameId);
}
