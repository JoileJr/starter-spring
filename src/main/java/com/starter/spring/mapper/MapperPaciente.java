package com.starter.spring.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

 import com.starter.spring.dto.PacienteDTO;
import com.starter.spring.dto.PessoaDTO;
import com.starter.spring.model.Paciente;
import com.starter.spring.model.Pessoa;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MapperPaciente {
    
    PacienteDTO toDto(Paciente entity);

    Paciente toEntity(PacienteDTO dto);

    List<PacienteDTO> toListDto(List<Paciente> entity);

    List<Paciente> toListEntity(List<PacienteDTO> dto);

    PessoaDTO toDto(Pessoa entity);

    Pessoa toEntity(PessoaDTO dto);

}
