package com.starter.spring.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.starter.spring.dto.PessoaDTO;
import com.starter.spring.model.Pessoa;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MapperPessoa {
    
    PessoaDTO toDto(Pessoa entity);

    Pessoa toEntity(PessoaDTO dto);

    List<PessoaDTO> toListDto(List<Pessoa> entity);

    List<Pessoa> toListEntity(List<PessoaDTO> dto);

}
