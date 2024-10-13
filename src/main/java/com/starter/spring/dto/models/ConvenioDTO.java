package com.starter.spring.dto.models;

import com.starter.spring.model.Convenio;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ConvenioDTO {
    
    private Long id;

    @NotNull(message = "O campo nome é requerido")
    private String nome;

    @NotNull(message = "O campo tipo é requerido")
    private String tipo;

    @NotNull(message = "O campo telefone é requerido")
    private String telefone;

    @NotNull(message = "O campo email é requerido")
    private String email;

    @NotNull(message = "O campo paciente é requerido")
    private PessoaDTO paciente;

    public static ConvenioDTO toDTO(Convenio convenio) {
        if (convenio == null) {
            return null;
        }

        ConvenioDTO dto = new ConvenioDTO();
        dto.setId(convenio.getId());
        dto.setNome(convenio.getNome());
        dto.setTipo(convenio.getTipo());
        dto.setTelefone(convenio.getTelefone());
        dto.setEmail(convenio.getEmail());
        dto.setPaciente(PessoaDTO.toDTO(convenio.getPaciente()));

        return dto;
    }

    public static Convenio toEntity(ConvenioDTO dto) {
        if (dto == null) {
            return null;
        }

        Convenio convenio = new Convenio();
        convenio.setId(dto.getId());
        convenio.setNome(dto.getNome());
        convenio.setTipo(convenio.getTipo());
        convenio.setTelefone(convenio.getTelefone());
        convenio.setEmail(convenio.getEmail());
        convenio.setPaciente(PessoaDTO.toEntity(dto.getPaciente()));

        return convenio;
    }

}
