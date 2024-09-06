package com.starter.spring.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ProntuarioDTO {
    
    private Integer id;

    private String historicoPessoal;

    private String historicoFamiliar;

    private String historicoSocial;

    private String anamnese;

    private String diagnostico;

    private String planoTratamento;

    private String resumoAlta;

    private String orientacoesPosAlta;

    private String observacoesFinais;

    private Date dataProntuario;

    private Float peso;

    private Float altura;

}
