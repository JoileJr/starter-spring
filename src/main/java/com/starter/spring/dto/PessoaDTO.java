package com.starter.spring.dto;

import java.util.Date;
import org.hibernate.validator.constraints.br.CPF;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
public class PessoaDTO {
    private Long id;

    @NotNull(message = "O campo username é requerido")
    private String username;

    @NotNull(message = "O campo email é requerido")
    @Email(message = "Email inválido")
    private String email;

    @NotNull(message = "O campo telefone é requerido")
    private String telefone;

    @NotNull(message = "O campo data nascimento é requerido")
    private Date dataNascimento;

    @NotNull(message = "O campo sexo é requerido")
    private String sexo;

    @NotNull(message = "O campo cpf é requerido")
    @CPF(message = "Cpf inválido")
    private String cpf;

    @NotNull(message = "O campo rg é requerido")
    private String rg;

    private EnderecoDTO endereco;
}
