package com.starter.spring.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.starter.spring.dto.EnderecoDTO;
import com.starter.spring.model.Endereco;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MapperEndereco {
    
    EnderecoDTO toDto(Endereco entity);

    Endereco toEntity(EnderecoDTO dto);

    List<EnderecoDTO> toListDto(List<Endereco> entity);

    List<EnderecoDTO> toListEntity(List<Endereco> dto);
}
