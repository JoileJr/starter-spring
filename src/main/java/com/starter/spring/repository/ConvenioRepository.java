package com.starter.spring.repository;

import com.starter.spring.model.Convenio;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConvenioRepository extends JpaRepository<Convenio, Long> {
}
