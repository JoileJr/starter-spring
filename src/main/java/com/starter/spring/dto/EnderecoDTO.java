package com.starter.spring.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EnderecoDTO {

    private Long id;

    @NotNull(message = "O campo cep é requerido")
    private String cep;

    @NotNull(message = "O campo estado é requerido")
    private String estado;

    @NotNull(message = "O campo cidade é requerido")
    private String cidade;
    
    @NotNull(message = "O campo bairro é requerido")
    private String bairro;

    @NotNull(message = "O campo logradouro é requerido")
    private String logradouro;

    @NotNull(message = "O campo número é requerido")
    private String numero;

    private String complemento;
}
