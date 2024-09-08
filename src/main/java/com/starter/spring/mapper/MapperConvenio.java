package com.starter.spring.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.starter.spring.dto.ConvenioDTO;
import com.starter.spring.model.Convenio;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MapperConvenio {
    
    ConvenioDTO toDto(Convenio entity);

    Convenio toEntity(ConvenioDTO dto);

    List<ConvenioDTO> toListDto(List<Convenio> entity);

    List<Convenio> toListEntity(List<ConvenioDTO> dto);

}
