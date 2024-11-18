package com.starter.spring.dto.useCases;


import com.starter.spring.dto.models.EnderecoDTO;
import com.starter.spring.model.Laboratorio;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Data
public class LaboratorioCreateRequest {
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

    private Long idAdm;

    public static Laboratorio toEntity(LaboratorioCreateRequest dto) {
        if (dto == null) {
            return null;
        }

        Laboratorio laboratorio = new Laboratorio();
        laboratorio.setId(dto.getId());
        laboratorio.setNome(dto.getNome());
        laboratorio.setCnpj(dto.getCnpj());
        laboratorio.setTelefone(dto.getTelefone());
        laboratorio.setRazaoSocial(dto.getRazaoSocial());
        laboratorio.setEmail(dto.getEmail());
        laboratorio.setEndereco(EnderecoDTO.toEntity(dto.getEndereco()));
        return laboratorio;
    }

}
