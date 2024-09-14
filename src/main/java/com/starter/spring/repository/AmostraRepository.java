package com.starter.spring.repository;

import com.starter.spring.model.Amostra;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AmostraRepository extends JpaRepository<Amostra, Long> {
}
