package com.starter.spring.dto;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.starter.spring.model.Perfil;
import com.starter.spring.model.Pessoa;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
public class PessoaDTO {
    private Long id;

    @NotNull(message = "O campo nome é requerido")
    private String nome;

    @NotNull(message = "O campo cpf é requerido")
    private String cpf;

    @NotNull(message = "O campo telefone é requerido")
    private String telefone;

    @NotNull(message = "O campo sexo é requerido")
    private String sexo;

    @NotNull(message = "O campo email é requerido")
    private String email;

    @NotNull(message = "O campo dataNascimento é requerido")
    private Date dataNascimento;

    private Set<PerfilDTO> perfis;

    public static PessoaDTO toDTO(Pessoa pessoa) {
        if (pessoa == null) {
            return null;
        }

        PessoaDTO dto = new PessoaDTO();
        dto.setId(pessoa.getId());
        dto.setNome(pessoa.getNome());
        dto.setCpf(pessoa.getCpf());
        dto.setTelefone(pessoa.getTelefone());
        dto.setSexo(pessoa.getSexo());
        dto.setEmail(pessoa.getEmail());
        dto.setDataNascimento(pessoa.getDataNascimento());
        dto.setPerfis(pessoa.getPerfis().stream()
                        .map(PerfilDTO::toDTO)
                        .collect(Collectors.toSet()));


        return dto;
    }

    public static Pessoa toEntity(PessoaDTO dto) {
        if (dto == null) {
            return null;
        }

        Pessoa pessoa = new Pessoa();
        pessoa.setId(dto.getId());
        pessoa.setNome(dto.getNome());
        pessoa.setCpf(dto.getCpf());
        pessoa.setTelefone(dto.getTelefone());
        pessoa.setSexo(dto.getSexo());
        pessoa.setEmail(dto.getEmail());
        pessoa.setDataNascimento(dto.getDataNascimento());
        pessoa.setPerfis(dto.getPerfis().stream()
                        .map(PerfilDTO::toEntity)
                        .collect(Collectors.toSet()));

        return pessoa;
    }

}
