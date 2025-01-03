package com.starter.spring.dto.models;


import com.starter.spring.model.Laboratorio;

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

    public static LaboratorioDTO toDTO(Laboratorio laboratorio) {
        if (laboratorio == null) {
            return null;
        }

        LaboratorioDTO dto = new LaboratorioDTO();
        dto.setId(laboratorio.getId());
        dto.setNome(laboratorio.getNome());
        dto.setCnpj(laboratorio.getCnpj());
        dto.setTelefone(laboratorio.getTelefone());
        dto.setRazaoSocial(laboratorio.getRazaoSocial());
        dto.setEmail(laboratorio.getEmail());
        dto.setEndereco(EnderecoDTO.toDTO(laboratorio.getEndereco()));

        return dto;
    }

    public static Laboratorio toEntity(LaboratorioDTO dto) {
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
