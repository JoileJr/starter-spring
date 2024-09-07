package com.starter.spring.dto;

import java.util.Date;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AmostraDTO {

    private Long id;

    @NotNull(message = "A data da coleta é requerido")
    private Date dateColeta;

    @NotNull(message = "O tipo de amostra é requerido")
    private String tipoAmostra;

    @NotNull(message = "O campo exame é requerido")
    private ExameDTO exame;
    
}
