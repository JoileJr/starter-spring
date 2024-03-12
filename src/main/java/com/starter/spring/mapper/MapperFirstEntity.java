package com.starter.spring.mapper;

import com.starter.spring.dto.FirstEntityDTO;
import com.starter.spring.model.FirstEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MapperFirstEntity {

    FirstEntityDTO toDto(FirstEntity entity);

    FirstEntity toEntity(FirstEntityDTO dto);

    List<FirstEntityDTO> toListDto(List<FirstEntity> entity);

    List<FirstEntity> toListEntity(List<FirstEntityDTO> dto);

}
