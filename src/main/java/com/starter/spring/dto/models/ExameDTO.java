package com.starter.spring.dto.models;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.starter.spring.model.Exame;

import lombok.Data;

@Data
public class ExameDTO {
    
    private Long id;

    private Date dataExame;

    private PacienteDTO paciente;

    private EnfermeiroDTO enfermeiro;

    private LaboratorioDTO laboratorio;

    private TipoExameDTO tipoExame;

    private List<ResultadoParametroDTO> resultadoParametros;

    public static ExameDTO toDTO(Exame exame) {
        if (exame == null) {
            return null;
        }

        ExameDTO dto = new ExameDTO();
        dto.setId(exame.getId());
        dto.setDataExame(exame.getDataExame());
        dto.setPaciente(PacienteDTO.toDTO(exame.getPaciente()));
        dto.setEnfermeiro(EnfermeiroDTO.toDTO(exame.getEnfermeiro()));
        dto.setLaboratorio(LaboratorioDTO.toDTO(exame.getLaboratorio()));
        dto.setTipoExame(TipoExameDTO.toDTO(exame.getTipoExame()));
        dto.setResultadoParametros(exame.getResultadoParametros().stream()
                        .map(ResultadoParametroDTO::toDTO)
                        .collect(Collectors.toList()));

        return dto;
    }

    public static Exame toEntity(ExameDTO dto) {
        if (dto == null) {
            return null;
        }

        Exame exame = new Exame();
        exame.setId(dto.getId());
        exame.setDataExame(dto.getDataExame());
        exame.setPaciente(PacienteDTO.toEntity(dto.getPaciente()));
        exame.setEnfermeiro(EnfermeiroDTO.toEntity(dto.getEnfermeiro()));
        exame.setLaboratorio(LaboratorioDTO.toEntity(dto.getLaboratorio()));
        exame.setTipoExame(TipoExameDTO.toEntity(dto.getTipoExame()));
        exame.setResultadoParametros(dto.getResultadoParametros().stream()
                        .map(ResultadoParametroDTO::toEntity)
                        .collect(Collectors.toList()));

        return exame;
    }

}
