package com.starter.spring.dto;

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
}
