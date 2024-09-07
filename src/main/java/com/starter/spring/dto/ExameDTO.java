package com.starter.spring.dto;

import java.util.Date;
import java.util.List;

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

}
