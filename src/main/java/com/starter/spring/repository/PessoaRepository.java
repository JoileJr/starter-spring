package com.starter.spring.repository;

import com.starter.spring.model.Pessoa;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    Optional<Pessoa> findByCpfOrEmail(String cpf, String email);

    UserDetails findByCpf(String cpf);

    @Query("SELECT p FROM Pessoa p WHERE " +
            "(:nome IS NULL OR LOWER(p.nome) LIKE LOWER(CONCAT('%', :nome, '%'))) AND " +
            "(:cpf IS NULL OR p.cpf = :cpf) AND " +
            "(:dataInicial IS NULL OR p.dataNascimento >= :dataInicial) AND " +
            "(:dataFinal IS NULL OR p.dataNascimento <= :dataFinal)")
    List<Pessoa> findPersonsByFilter(@Param("nome") String nome,
                                        @Param("cpf") String cpf,
                                        @Param("dataInicial") Date dataInicial,
                                        @Param("dataFinal") Date dataFinal);
}
