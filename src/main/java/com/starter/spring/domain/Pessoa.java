package com.starter.spring.domain;

import java.util.Date;

import com.starter.spring.domain.enums.Perfil;
import com.starter.spring.infra.dto.PessoaDTO;
import com.starter.spring.infra.persistence.jpa.pessoa.PessoaEntity;

public record Pessoa(
    Long id,
    String username,
    String email,
    String telefone,
    Date dataNascimento,
    String sexo,
    String cpf,
    String rg,
    String cns,
    String senha,
    Perfil perfil,
    Boolean ativo,
    Endereco endereco
) {
    public PessoaDTO toDTO() {
        PessoaDTO dto = new PessoaDTO();
        dto.setId(this.id());
        dto.setUsername(this.username());
        dto.setEmail(this.email());
        dto.setTelefone(this.telefone());
        dto.setDataNascimento(this.dataNascimento());
        dto.setSexo(this.sexo());
        dto.setCpf(this.cpf());
        dto.setRg(this.rg());
        dto.setCns(this.cns());
        dto.setSenha(this.senha());
        dto.setPerfil(this.perfil());
        dto.setAtivo(this.ativo());
        dto.setEndereco(this.endereco().toDTO());
        return dto;
    }

    public PessoaEntity toEntidade() {
        PessoaEntity dto = new PessoaEntity();
        dto.setId(this.id());
        dto.setUsername(this.username());
        dto.setEmail(this.email());
        dto.setTelefone(this.telefone());
        dto.setDataNascimento(this.dataNascimento());
        dto.setSexo(this.sexo());
        dto.setCpf(this.cpf());
        dto.setRg(this.rg());
        dto.setCns(this.cns());
        dto.setSenha(this.senha());
        dto.setPerfil(this.perfil());
        dto.setAtivo(this.ativo());
        dto.setEndereco(this.endereco().toEntity());
        return dto;
    }
}