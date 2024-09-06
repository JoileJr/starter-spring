package com.starter.spring.dto;


import java.util.List;


public class PacienteDTO extends PessoaDTO {

    private List<ConvenioDTO> convenios;

    private List<ProntuarioDTO> prontuarios;

    public List<ConvenioDTO> getConvenios() {
        return convenios;
    }

    public void setConvenios(List<ConvenioDTO> convenios) {
        this.convenios = convenios;
    }

    public List<ProntuarioDTO> getProntuarios() {
        return prontuarios;
    }

    public void setProntuarios(List<ProntuarioDTO> prontuarios) {
        this.prontuarios = prontuarios;
    }

}
