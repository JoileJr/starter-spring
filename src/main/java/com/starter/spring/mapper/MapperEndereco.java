package com.starter.spring.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.starter.spring.domain.Endereco;
import com.starter.spring.infra.dto.EnderecoDTO;
import com.starter.spring.infra.persistence.jpa.endereco.EnderecoEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MapperEndereco {
    
    Endereco toDomain(EnderecoEntity entity);

    EnderecoEntity toEntity(Endereco dto);

    List<Endereco> toListDomain(List<EnderecoEntity> entity);

    List<Endereco> toListEntity(List<EnderecoEntity> dto);

    EnderecoDTO toDto(Endereco entity);

    Endereco toEntity(EnderecoDTO dto);

    List<EnderecoDTO> toListDto(List<Endereco> entity);

}
