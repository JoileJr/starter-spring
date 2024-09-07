package com.starter.spring.dto;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TipoExameDTO {

    private Long id;

    @NotNull(message = "O nome é requerido")
    private String nome;

    @NotNull(message = "O descricao é requerido")
    private String descricao;

    private List<ExameDTO> exames;

    private List<ParametroDTO> parametros;
    
}
