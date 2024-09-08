package com.starter.spring.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.starter.spring.dto.ResultadoParametroDTO;
import com.starter.spring.model.ResultadoParametro;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MapperResultadoParametro {
    
    ResultadoParametroDTO toDto(ResultadoParametro entity);

    ResultadoParametro toEntity(ResultadoParametroDTO dto);

    List<ResultadoParametroDTO> toListDto(List<ResultadoParametro> entity);

    List<ResultadoParametro> toListEntity(List<ResultadoParametroDTO> dto);
    
}
