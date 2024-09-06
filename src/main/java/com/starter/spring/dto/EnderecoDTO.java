package com.starter.spring.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EnderecoDTO {

    private Long id;

    @NotNull(message = "O campo cep é requerido")
    private String cep;

    @NotNull(message = "O campo state é requerido")
    private String state;

    @NotNull(message = "O campo city é requerido")
    private String city;
    
    @NotNull(message = "O campo neighborhood é requerido")
    private String neighborhood;

    @NotNull(message = "O campo publicPlace é requerido")
    private String publicPlace;

    @NotNull(message = "O campo number é requerido")
    private String number;

    private String complement;
}
