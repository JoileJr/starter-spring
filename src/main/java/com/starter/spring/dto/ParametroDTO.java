package com.starter.spring.dto;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ParametroDTO {

    private Long id;

    @NotNull(message = "O campo email é requerido")
    private String nome;

    @NotNull(message = "A unidade de medida é requerido")
    private String unidadeDeMedida;

    @NotNull(message = "O valor referencia minimo é requerido")
    private String valorReferenciaMinimo;

    @NotNull(message = "O valor referencia maximo é requerido")
    private String valorReferenciaMaximo;

    private TipoExameDTO tipoExame;

    private List<ResultadoParametroDTO> ResultadoParametros;
    
}
