package com.starter.spring.dto;

import java.util.stream.Collectors;

import com.starter.spring.model.Administrativo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdministrativoDTO extends PessoaDTO {
 
    private LaboratorioDTO laboratorio;

    public static Administrativo toEntity(AdministrativoDTO dto) {
        if (dto == null) {
            return null;
        }

        Administrativo administrativo = new Administrativo();
        administrativo.setId(dto.getId());
        administrativo.setNome(dto.getNome());
        administrativo.setCpf(dto.getCpf());
        administrativo.setTelefone(dto.getTelefone());
        administrativo.setSexo(dto.getSexo());
        administrativo.setEmail(dto.getEmail());
        administrativo.setDataNascimento(dto.getDataNascimento());
        administrativo.setPerfis(dto.getPerfis().stream()
                        .map(PerfilDTO::toEntity)
                        .collect(Collectors.toSet()));
        administrativo.setLaboratorio(LaboratorioDTO.toEntity(dto.getLaboratorio()));

        return administrativo;
    }

    public static AdministrativoDTO toDTO(Administrativo administrativo) {
        if (administrativo == null) {
            return null;
        }

        AdministrativoDTO dto = new AdministrativoDTO();
        dto.setId(administrativo.getId());
        dto.setNome(administrativo.getNome());
        dto.setCpf(administrativo.getCpf());
        dto.setTelefone(administrativo.getTelefone());
        dto.setSexo(administrativo.getSexo());
        dto.setEmail(administrativo.getEmail());
        dto.setDataNascimento(administrativo.getDataNascimento());
        dto.setPerfis(administrativo.getPerfis().stream()
                        .map(PerfilDTO::toDTO)
                        .collect(Collectors.toSet()));
        dto.setLaboratorio(LaboratorioDTO.toDTO(administrativo.getLaboratorio()));

        return dto;
    }

}
