package com.starter.spring.dto;


import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Data
public class LaboratorioDTO {
    private Long id;

    @NotNull(message = "O campo nome é requerido")
    private String nome;

    @NotNull(message = "O campo cnpj é requerido")
    private String cnpj;

    @NotNull(message = "O campo telefone é requerido")
    private String telefone;

    @NotNull(message = "O campo razaoSocial é requerido")
    private String razaoSocial;

    @NotNull(message = "O campo email é requerido")
    private String email;

    private EnderecoDTO endereco;

    private List<EnfermeiroDTO> enfermeiros;

    private List<AdministrativoDTO> administrativos;

    private List<ExameDTO> exames;

}
