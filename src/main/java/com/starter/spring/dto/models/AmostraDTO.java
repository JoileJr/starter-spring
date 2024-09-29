package com.starter.spring.dto.models;

import java.util.Date;

import com.starter.spring.model.Amostra;

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
    
    public static Amostra toEntity(AmostraDTO dto) {
        if (dto == null) {
            return null;
        }

        Amostra amostra = new Amostra();
        amostra.setId(dto.getId());
        amostra.setDateColeta(dto.getDateColeta());
        amostra.setTipoAmostra(dto.getTipoAmostra());

        if (dto.getExame() != null) {
            amostra.setExame(ExameDTO.toEntity(dto.getExame()));
        }

        return amostra;
    }

    public static AmostraDTO toDTO(Amostra amostra) {
        if (amostra == null) {
            return null;
        }

        AmostraDTO dto = new AmostraDTO();
        dto.setId(amostra.getId());
        dto.setDateColeta(amostra.getDateColeta());
        dto.setTipoAmostra(amostra.getTipoAmostra());

        if (amostra.getExame() != null) {
            dto.setExame(ExameDTO.toDTO(amostra.getExame()));
        }

        return dto;
    }
}
