package com.starter.spring.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "prontuario")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Prontuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "historicoPessoal")
    private String historicoPessoal;

    @Column(name = "historicoFamiliar")
    private String historicoFamiliar;

    @Column(name = "historicoSocial")
    private String historicoSocial;

    @Column(name = "anamnese")
    private String anamnese;

    @Column(name = "diagnostico")
    private String diagnostico;

    @Column(name = "planoTratamento")
    private String planoTratamento;

    @Column(name = "resumoAlta")
    private String resumoAlta;

    @Column(name = "orientacoesPosAlta")
    private String orientacoesPosAlta;

    @Column(name = "observacoesFinais")
    private String observacoesFinais;

    @Column(name = "dataProntuario")
    private Date dataProntuario;

    @Column(name = "peso")
    private Float peso;

    @Column(name = "altura")
    private Float altura;

    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Pessoa paciente;
}
