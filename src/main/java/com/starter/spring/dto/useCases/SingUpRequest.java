package com.starter.spring.dto.useCases;

import java.util.Date;
import java.util.List;
import com.starter.spring.model.Pessoa;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SingUpRequest {

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

    @NotNull(message = "O campo senha é requerido")
    private String senha;

    @NotNull(message = "O campo dataNascimento é requerido")
    private Date dataNascimento;

    private String perfis;

    public static SingUpRequest toDTO(Pessoa pessoa) {
        if (pessoa == null) {
            return null;
        }

        SingUpRequest dto = new SingUpRequest();
        dto.setId(pessoa.getId());
        dto.setNome(pessoa.getNome());
        dto.setCpf(pessoa.getCpf());
        dto.setTelefone(pessoa.getTelefone());
        dto.setSexo(pessoa.getSexo());
        dto.setEmail(pessoa.getEmail());
        dto.setSenha(pessoa.getSenha());
        dto.setDataNascimento(pessoa.getDataNascimento());
        return dto;
    }

    public static Pessoa toEntity(SingUpRequest dto) {
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
        pessoa.setSenha(dto.getSenha());
        pessoa.setDataNascimento(dto.getDataNascimento());
        return pessoa;
    }
    
}
