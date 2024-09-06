package com.starter.spring.dto;


import java.util.Date;
import java.util.Set;

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

}
