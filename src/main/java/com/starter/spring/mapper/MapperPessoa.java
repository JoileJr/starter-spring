package com.starter.spring.mapper;

import com.starter.spring.domain.Pessoa;
import com.starter.spring.infra.dto.PessoaDTO;
import com.starter.spring.infra.persistence.jpa.pessoa.PessoaEntity;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MapperPessoa {

    Pessoa toDomain(PessoaEntity entity);

    PessoaEntity toEntity(Pessoa dto);

    List<Pessoa> toListDomain(List<PessoaEntity> entity);

    List<PessoaEntity> toListEntity(List<Pessoa> dto);
    
    PessoaDTO toDto(Pessoa entity);

    Pessoa toEntity(PessoaDTO dto);

    List<PessoaDTO> toListDto(List<Pessoa> entity);

}