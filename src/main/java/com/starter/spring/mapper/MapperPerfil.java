package com.starter.spring.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.starter.spring.dto.PerfilDTO;
import com.starter.spring.model.Perfil;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MapperPerfil {
    
    PerfilDTO toDto(Perfil entity);

    Perfil toEntity(PerfilDTO dto);

    List<PerfilDTO> toListDto(List<Perfil> entity);

    List<Perfil> toListEntity(List<PerfilDTO> dto);

}
