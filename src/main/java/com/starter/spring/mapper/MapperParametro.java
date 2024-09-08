package com.starter.spring.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.starter.spring.dto.ParametroDTO;
import com.starter.spring.model.Parametro;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MapperParametro {
    
    ParametroDTO toDto(Parametro entity);

    Parametro toEntity(ParametroDTO dto);

    List<ParametroDTO> toListDto(List<Parametro> entity);

    List<Parametro> toListEntity(List<ParametroDTO> dto);

}
