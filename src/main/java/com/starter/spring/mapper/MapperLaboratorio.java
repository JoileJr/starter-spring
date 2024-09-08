package com.starter.spring.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.starter.spring.dto.LaboratorioDTO;
import com.starter.spring.model.Laboratorio;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MapperLaboratorio {
    
    LaboratorioDTO toDto(Laboratorio entity);

    Laboratorio toEntity(LaboratorioDTO dto);

    List<LaboratorioDTO> toListDto(List<Laboratorio> entity);

    List<Laboratorio> toListEntity(List<LaboratorioDTO> dto);

}
