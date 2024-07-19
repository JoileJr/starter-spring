package com.starter.spring.mapper;

import com.starter.spring.dto.PessoaDTO;
import com.starter.spring.model.Pessoa;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MapperPessoa {

    PessoaDTO toDto(Pessoa entity);

    Pessoa toEntity(PessoaDTO dto);

    List<PessoaDTO> toListDto(List<Pessoa> entity);

    List<PessoaDTO> toListEntity(List<Pessoa> dto);

}