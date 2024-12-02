package com.starter.spring.dto.models;

import com.starter.spring.model.TipoExame;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TipoExameDTO {

    private Long id;

    @NotNull(message = "O nome é requerido")
    private String nome;

    @NotNull(message = "O descricao é requerido")
    private String descricao;

    public static TipoExame toEntity(TipoExameDTO dto) {
        if (dto == null) {
            return null;
        }

        TipoExame tipoExame = new TipoExame();
        tipoExame.setId(dto.getId());
        tipoExame.setNome(dto.getNome());
        tipoExame.setDescricao(dto.getDescricao());

        return tipoExame;
    }

    public static TipoExameDTO toDTO(TipoExame tipoExame) {
        if (tipoExame == null) {
            return null;
        }

        TipoExameDTO dto = new TipoExameDTO();
        dto.setId(tipoExame.getId());
        dto.setNome(tipoExame.getNome());
        dto.setDescricao(tipoExame.getDescricao());

        return dto;
    }

}
