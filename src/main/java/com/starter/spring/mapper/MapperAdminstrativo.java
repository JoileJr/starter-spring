package com.starter.spring.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.starter.spring.dto.AdministrativoDTO;
import com.starter.spring.dto.PessoaDTO;
import com.starter.spring.model.Administrativo;
import com.starter.spring.model.Pessoa;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MapperAdminstrativo {

    AdministrativoDTO toDto(Administrativo entity);

    Administrativo toEntity(AdministrativoDTO dto);

    List<AdministrativoDTO> toListDto(List<Administrativo> entity);

    List<Administrativo> toListEntity(List<AdministrativoDTO> dto);

    PessoaDTO toDto(Pessoa entity);

    Pessoa toEntity(PessoaDTO dto);
    
}
