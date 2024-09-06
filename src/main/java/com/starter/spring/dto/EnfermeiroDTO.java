package com.starter.spring.dto;

import jakarta.validation.constraints.NotNull;

public class EnfermeiroDTO extends PessoaDTO {
    
    @NotNull(message = "O campo coren é requerido")
    private String coren;

    private LaboratorioDTO laboratorio;

    public LaboratorioDTO getLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(LaboratorioDTO laboratorio) {
        this.laboratorio = laboratorio;
    }

}
