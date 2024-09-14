package com.starter.spring.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.starter.spring.model.Parametro;
import com.starter.spring.model.ResultadoParametro;

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

        // Conversão do TipoExame
        if (dto.getTipoExame() != null) {
            parametro.setTipoExame(TipoExameDTO.toEntity(dto.getTipoExame()));
        }

        // Conversão dos ResultadoParametros
        if (dto.getResultadoParametros() != null) {
            List<ResultadoParametro> resultadoParametros = dto.getResultadoParametros().stream()
                .map(ResultadoParametroDTO::toEntity)
                .collect(Collectors.toList());
            parametro.setResultadoParametros(resultadoParametros);
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

        if (parametro.getTipoExame() != null) {
            dto.setTipoExame(TipoExameDTO.toDTO(parametro.getTipoExame()));
        }

        if (parametro.getResultadoParametros() != null) {
            List<ResultadoParametroDTO> resultadoParametros = parametro.getResultadoParametros().stream()
                .map(ResultadoParametroDTO::toDTO)
                .collect(Collectors.toList());
            dto.setResultadoParametros(resultadoParametros);
        }

        return dto;
    }
    
}
