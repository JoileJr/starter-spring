package com.starter.spring.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.starter.spring.model.Paciente;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PacienteDTO extends PessoaDTO {

    private List<ConvenioDTO> convenios;

    private List<ProntuarioDTO> prontuarios;

    private List<ExameDTO> exames;

    public static PacienteDTO toDTO(Paciente paciente) {
        if (paciente == null) {
            return null;
        }

        PacienteDTO dto = new PacienteDTO();
        dto.setId(paciente.getId());
        dto.setNome(paciente.getNome());
        dto.setCpf(paciente.getCpf());
        dto.setTelefone(paciente.getTelefone());
        dto.setSexo(paciente.getSexo());
        dto.setEmail(paciente.getEmail());
        dto.setDataNascimento(paciente.getDataNascimento());
        dto.setPerfis(paciente.getPerfis().stream()
                        .map(PerfilDTO::toDTO)
                        .collect(Collectors.toSet()));
        dto.setConvenios(paciente.getConvenios().stream()
                        .map(ConvenioDTO::toDTO)
                        .collect(Collectors.toList()));
        dto.setProntuarios(paciente.getProntuarios().stream()
                        .map(ProntuarioDTO::toDTO)
                        .collect(Collectors.toList()));
        dto.setExames(paciente.getExames().stream()
                        .map(ExameDTO::toDTO)
                        .collect(Collectors.toList()));

        return dto;
    }

    public static Paciente toEntity(PacienteDTO dto) {
        if (dto == null) {
            return null;
        }

        Paciente paciente = new Paciente();
        paciente.setId(dto.getId());
        paciente.setNome(dto.getNome());
        paciente.setCpf(dto.getCpf());
        paciente.setTelefone(dto.getTelefone());
        paciente.setSexo(dto.getSexo());
        paciente.setEmail(dto.getEmail());
        paciente.setDataNascimento(dto.getDataNascimento());
        paciente.setPerfis(dto.getPerfis().stream()
                        .map(PerfilDTO::toEntity)
                        .collect(Collectors.toSet()));
        paciente.setConvenios(dto.getConvenios().stream()
                        .map(ConvenioDTO::toEntity)
                        .collect(Collectors.toList()));
        paciente.setProntuarios(dto.getProntuarios().stream()
                        .map(ProntuarioDTO::toEntity)
                        .collect(Collectors.toList()));
        paciente.setExames(dto.getExames().stream()
                        .map(ExameDTO::toEntity)
                        .collect(Collectors.toList()));

        return paciente;
    }
}