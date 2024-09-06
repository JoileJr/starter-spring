package com.starter.spring.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ConvenioDTO {
    
    private Long id;

    @NotNull(message = "O campo nome é requerido")
    private String nome;

    @NotNull(message = "O campo tipo é requerido")
    private String tipo;

    @NotNull(message = "O campo telefone é requerido")
    private String telefone;

    @NotNull(message = "O campo email é requerido")
    private String email;

}
