package com.starter.spring.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.starter.spring.dto.ProntuarioDTO;
import com.starter.spring.model.Prontuario;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MapperProntuario {
    
    ProntuarioDTO toDto(Prontuario entity);

    Prontuario toEntity(ProntuarioDTO dto);

    List<ProntuarioDTO> toListDto(List<Prontuario> entity);

    List<Prontuario> toListEntity(List<ProntuarioDTO> dto);

}
