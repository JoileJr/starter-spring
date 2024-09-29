package com.starter.spring.dto.models;

import com.starter.spring.model.ResultadoParametro;

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

    public static ResultadoParametro toEntity(ResultadoParametroDTO dto) {
        if (dto == null) {
            return null;
        }

        ResultadoParametro resultadoParametro = new ResultadoParametro();
        resultadoParametro.setId(dto.getId());
        resultadoParametro.setResultado(dto.getResultado());
        resultadoParametro.setObservacao(dto.getObservacao());
        resultadoParametro.setNivelDeAlerta(dto.getNivelDeAlerta());

        if (dto.getParametro() != null) {
            resultadoParametro.setParametro(ParametroDTO.toEntity(dto.getParametro()));
        }

        if (dto.getExame() != null) {
            resultadoParametro.setExame(ExameDTO.toEntity(dto.getExame()));
        }

        return resultadoParametro;
    }

    public static ResultadoParametroDTO toDTO(ResultadoParametro resultadoParametro) {
        if (resultadoParametro == null) {
            return null;
        }

        ResultadoParametroDTO dto = new ResultadoParametroDTO();
        dto.setId(resultadoParametro.getId());
        dto.setResultado(resultadoParametro.getResultado());
        dto.setObservacao(resultadoParametro.getObservacao());
        dto.setNivelDeAlerta(resultadoParametro.getNivelDeAlerta());

        if (resultadoParametro.getParametro() != null) {
            dto.setParametro(ParametroDTO.toDTO(resultadoParametro.getParametro()));
        }

        if (resultadoParametro.getExame() != null) {
            dto.setExame(ExameDTO.toDTO(resultadoParametro.getExame()));
        }

        return dto;
    }

}
