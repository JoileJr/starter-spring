package com.starter.spring.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.starter.spring.dto.EnfermeiroDTO;
import com.starter.spring.dto.PessoaDTO;
import com.starter.spring.model.Enfermeiro;
import com.starter.spring.model.Pessoa;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MapperEnfermeiro {
    
    EnfermeiroDTO toDto(Enfermeiro entity);

    Enfermeiro toEntity(EnfermeiroDTO dto);

    List<EnfermeiroDTO> toListDto(List<Enfermeiro> entity);

    List<Enfermeiro> toListEntity(List<EnfermeiroDTO> dto);

    PessoaDTO toDto(Pessoa entity);

    Pessoa toEntity(PessoaDTO dto);

}
