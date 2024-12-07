package com.starter.spring.dto.models;

import com.starter.spring.model.Parametro;

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

    @NotNull(message = "O valor referencia maximo é requerido")
    private String mascara;

    private TipoExameDTO tipoExame;

    public static Parametro toEntity(ParametroDTO dto) {
        if (dto == null) {
            return null;
        }

        Parametro parametro = new Parametro();
        parametro.setId(dto.getId());
        parametro.setNome(dto.getNome());
        parametro.setUnidadeDeMedida(dto.getUnidadeDeMedida());
        parametro.setValorReferenciaMinimo(dto.getValorReferenciaMinimo());
        parametro.setValorReferenciaMaximo(dto.getValorReferenciaMaximo());
        parametro.setMascara(dto.getMascara());

        // Conversão do TipoExame
        if (dto.getTipoExame() != null) {
            parametro.setTipoExame(TipoExameDTO.toEntity(dto.getTipoExame()));
        }

        return parametro;
    }

    public static ParametroDTO toDTO(Parametro parametro) {
        if (parametro == null) {
            return null;
        }

        ParametroDTO dto = new ParametroDTO();
        dto.setId(parametro.getId());
        dto.setNome(parametro.getNome());
        dto.setUnidadeDeMedida(parametro.getUnidadeDeMedida());
        dto.setValorReferenciaMinimo(parametro.getValorReferenciaMinimo());
        dto.setValorReferenciaMaximo(parametro.getValorReferenciaMaximo());
        dto.setMascara(parametro.getMascara());

        if (parametro.getTipoExame() != null) {
            dto.setTipoExame(TipoExameDTO.toDTO(parametro.getTipoExame()));
        }

        return dto;
    }

}
