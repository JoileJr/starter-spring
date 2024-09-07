package com.starter.spring.repository;

import com.starter.spring.model.TipoExame;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoExameRepository extends JpaRepository<TipoExame, Long> {
}
