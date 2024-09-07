package com.starter.spring.dto;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnfermeiroDTO extends PessoaDTO {
    
    @NotNull(message = "O campo coren é requerido")
    private String coren;

    private LaboratorioDTO laboratorio;

    private List<ExameDTO> exames;

}
