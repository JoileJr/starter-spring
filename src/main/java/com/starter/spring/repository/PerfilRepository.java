package com.starter.spring.repository;

import com.starter.spring.model.Perfil;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Long> {
    Perfil findByNome(String nome);

    List<Perfil> findByNomeIn(List<String> nomes);
}
