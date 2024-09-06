package com.starter.spring.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "laboratorio")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Laboratorio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "cnpj", nullable = false)
    private String cnpj;

    @Column(name = "telefone", nullable = false)
    private String telefone;

    @Column(name = "razaoSocial", nullable = false)
    private String razaoSocial;

    @Column(name = "email", nullable = false)
    private String email;

    @OneToOne
    private Endereco endereco;

    @OneToMany(mappedBy = "laboratorio", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Enfermeiro> enfermeiros;

    @OneToMany(mappedBy = "laboratorio", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Administrativo> administrativos;

}
