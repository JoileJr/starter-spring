package com.starter.spring.dto.useCases;

import java.util.Date;

import com.starter.spring.dto.models.LaboratorioDTO;

import lombok.Data;

@Data
public class FilterHealthProfessionalRequest {
    private String nome;
    private String cpf;
    private Date dataInicio;
    private Date dataFim;
    private LaboratorioDTO laboratorio;
    private String email;
    private String telefone;
    private String registroProfissional;
    private String tipoProfissional;
}
