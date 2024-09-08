package com.starter.spring.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.starter.spring.dto.AmostraDTO;
import com.starter.spring.model.Amostra;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MapperAmostra {
    
    AmostraDTO toDto(Amostra entity);

    Amostra toEntity(AmostraDTO dto);

    List<AmostraDTO> toListDto(List<Amostra> entity);

    List<Amostra> toListEntity(List<AmostraDTO> dto);
    
}
