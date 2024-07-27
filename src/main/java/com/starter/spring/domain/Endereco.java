package com.starter.spring.domain;

import com.starter.spring.infra.dto.EnderecoDTO;
import com.starter.spring.infra.persistence.jpa.endereco.EnderecoEntity;

public record Endereco(
    Long id,
    String cep,
    String estado,
    String cidade,
    String bairro,
    String logradouro,
    String numero,
    String complemento
) {
    public EnderecoDTO toDTO() {
        EnderecoDTO dto = new EnderecoDTO();
        dto.setId(this.id());
        dto.setCep(cep);
        dto.setEstado(cep);
        dto.setBairro(cep);
        dto.setLogradouro(cep);
        dto.setNumero(cep);
        dto.setComplemento(cep);
        return dto;
    }

    public EnderecoEntity toEntity() {
        EnderecoEntity dto = new EnderecoEntity();
        dto.setId(this.id());
        dto.setCep(cep);
        dto.setEstado(cep);
        dto.setBairro(cep);
        dto.setLogradouro(cep);
        dto.setNumero(cep);
        dto.setComplemento(cep);
        return dto;
    }
}
