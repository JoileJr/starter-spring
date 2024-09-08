package com.starter.spring.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.starter.spring.dto.TipoExameDTO;
import com.starter.spring.model.TipoExame;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MapperTipoExame {

    TipoExameDTO toDto(TipoExame entity);

    TipoExame toEntity(TipoExameDTO dto);

    List<TipoExameDTO> toListDto(List<TipoExame> entity);

    List<TipoExame> toListEntity(List<TipoExameDTO> dto);
    
}
