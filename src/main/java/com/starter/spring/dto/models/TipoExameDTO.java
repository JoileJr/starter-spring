package com.starter.spring.dto.models;

import java.util.List;
import java.util.stream.Collectors;

import com.starter.spring.model.Exame;
import com.starter.spring.model.Parametro;
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

    private List<ExameDTO> exames;

    private List<ParametroDTO> parametros;

    public static TipoExame toEntity(TipoExameDTO dto) {
        if (dto == null) {
            return null;
        }

        TipoExame tipoExame = new TipoExame();
        tipoExame.setId(dto.getId());
        tipoExame.setNome(dto.getNome());
        tipoExame.setDescricao(dto.getDescricao());

        if (dto.getExames() != null) {
            List<Exame> exames = dto.getExames().stream()
                .map(ExameDTO::toEntity)
                .collect(Collectors.toList());
            tipoExame.setExames(exames);
        }

        if (dto.getParametros() != null) {
            List<Parametro> parametros = dto.getParametros().stream()
                .map(ParametroDTO::toEntity)
                .collect(Collectors.toList());
            tipoExame.setParametros(parametros);
        }

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

        if (tipoExame.getExames() != null) {
            List<ExameDTO> exames = tipoExame.getExames().stream()
                .map(ExameDTO::toDTO)
                .collect(Collectors.toList());
            dto.setExames(exames);
        }

        if (tipoExame.getParametros() != null) {
            List<ParametroDTO> parametros = tipoExame.getParametros().stream()
                .map(ParametroDTO::toDTO)
                .collect(Collectors.toList());
            dto.setParametros(parametros);
        }

        return dto;
    }
    
}
