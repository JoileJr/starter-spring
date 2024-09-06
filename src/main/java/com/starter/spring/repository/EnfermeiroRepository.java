package com.starter.spring.repository;

import com.starter.spring.model.Enfermeiro;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnfermeiroRepository extends JpaRepository<Enfermeiro, Long> {
}
