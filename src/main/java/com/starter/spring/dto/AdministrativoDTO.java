package com.starter.spring.dto;


public class AdministrativoDTO extends PessoaDTO {
 
    private LaboratorioDTO laboratorio;

    public LaboratorioDTO getLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(LaboratorioDTO laboratorio) {
        this.laboratorio = laboratorio;
    }
}
