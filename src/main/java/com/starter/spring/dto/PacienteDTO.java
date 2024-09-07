package com.starter.spring.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PacienteDTO extends PessoaDTO {

    private List<ConvenioDTO> convenios;

    private List<ProntuarioDTO> prontuarios;

    private List<ExameDTO> exames;

}
