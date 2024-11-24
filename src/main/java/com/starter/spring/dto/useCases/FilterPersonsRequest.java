package com.starter.spring.dto.useCases;

import java.util.Date;

import lombok.Data;

@Data
public class FilterPersonsRequest {
    private String nome;
    private String cpf;
    private Date dataInicio;
    private Date dataFim;
}
