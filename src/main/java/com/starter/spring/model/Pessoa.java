package com.starter.spring.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.starter.spring.enums.Perfil;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "Pessoa")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "telefone", nullable = false)
    private String telefone;

    @Column(name = "dataNascimento", nullable = false)
    private Date dataNascimento;

    @Column(name = "sexo", nullable = false)
    private String sexo;

    @Column(name = "cpf", nullable = false, unique = true)
    private String cpf;

    @Column(name = "rg", nullable = false)
    private String rg;

    @Column(name = "cns", nullable = false)
    private String cns;

    @Column(name = "senha", nullable = false)
    private String senha;

    @Column(name = "perfil", nullable = false)
    private Perfil perfil;

    @Column(name = "ativo", nullable = false)
    private Boolean ativo;

    @OneToOne
    private Endereco endereco;
}
