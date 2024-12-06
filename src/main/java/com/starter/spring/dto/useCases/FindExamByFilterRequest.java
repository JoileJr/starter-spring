package com.starter.spring.dto.useCases;

import java.util.Date;

import lombok.Data;

@Data
public class FindExamByFilterRequest {
    private Long tipoExame;
    private String cpf;
    private Date dataInicio;
    private Date dataFim;   
}
