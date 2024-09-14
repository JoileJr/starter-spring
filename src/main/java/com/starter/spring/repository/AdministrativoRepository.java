package com.starter.spring.repository;

import com.starter.spring.model.Administrativo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministrativoRepository extends JpaRepository<Administrativo, Long> {
}
