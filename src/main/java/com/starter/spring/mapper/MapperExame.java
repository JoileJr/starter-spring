package com.starter.spring.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.starter.spring.dto.ExameDTO;
import com.starter.spring.model.Exame;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MapperExame {
    
    ExameDTO toDto(Exame entity);

    Exame toEntity(ExameDTO dto);

    List<ExameDTO> toListDto(List<Exame> entity);

    List<Exame> toListEntity(List<ExameDTO> dto);
}
