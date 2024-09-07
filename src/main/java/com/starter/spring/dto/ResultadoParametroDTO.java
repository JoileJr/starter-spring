package com.starter.spring.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ResultadoParametroDTO {
    
    private Long id;

    @NotNull(message = "O resultado é requerido")
    private String resultado;

    private String observacao;

    @NotNull(message = "O nivel de alerta é requerido")
    private String nivelDeAlerta;

    private ParametroDTO parametro;

    private ExameDTO exame;

}
