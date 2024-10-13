package com.starter.spring.dto.models;

import java.util.List;
import java.util.stream.Collectors;

import com.starter.spring.enums.TipoUsuario;
import com.starter.spring.model.ProfissionalSaude;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfissionalSaudeDTO extends PessoaDTO {

    @NotNull(message = "O registro profissional é requerido")
    private String registroProfissional;

    private TipoUsuario tipoProfissional;

    @NotNull(message = "A região é requerida")
    private String regiao;

    private LaboratorioDTO laboratorio;

    private List<ExameDTO> exames;

    public static ProfissionalSaudeDTO toDTO(ProfissionalSaude enfermeiro) {
        if (enfermeiro == null) {
            return null;
        }

        ProfissionalSaudeDTO dto = new ProfissionalSaudeDTO();
        dto.setId(enfermeiro.getId());
        dto.setNome(enfermeiro.getNome());
        dto.setCpf(enfermeiro.getCpf());
        dto.setTelefone(enfermeiro.getTelefone());
        dto.setSexo(enfermeiro.getSexo());
        dto.setEmail(enfermeiro.getEmail());
        dto.setSenha(enfermeiro.getSenha());
        dto.setDataNascimento(enfermeiro.getDataNascimento());
        dto.setPerfis(enfermeiro.getPerfis().stream()
                        .map(PerfilDTO::toDTO)
                        .collect(Collectors.toSet()));
        dto.setRegistroProfissional(enfermeiro.getRegistroProfissional());
        dto.setTipoProfissional(enfermeiro.getTipoProfissional());
        dto.setRegiao(enfermeiro.getRegiao());
        dto.setLaboratorio(LaboratorioDTO.toDTO(enfermeiro.getLaboratorio()));
        dto.setExames(enfermeiro.getExames().stream()
                        .map(ExameDTO::toDTO)
                        .collect(Collectors.toList()));

        return dto;
    }

    public static ProfissionalSaude toEntity(ProfissionalSaudeDTO dto) {
        if (dto == null) {
            return null;
        }

        ProfissionalSaude enfermeiro = new ProfissionalSaude();
        enfermeiro.setId(dto.getId());
        enfermeiro.setNome(dto.getNome());
        enfermeiro.setCpf(dto.getCpf());
        enfermeiro.setTelefone(dto.getTelefone());
        enfermeiro.setSexo(dto.getSexo());
        enfermeiro.setEmail(dto.getEmail());
        enfermeiro.setSenha(dto.getSenha());
        enfermeiro.setDataNascimento(dto.getDataNascimento());
        enfermeiro.setPerfis(dto.getPerfis().stream()
                        .map(PerfilDTO::toEntity)
                        .collect(Collectors.toSet()));
        enfermeiro.setRegistroProfissional(dto.getRegistroProfissional());
        enfermeiro.setTipoProfissional(dto.getTipoProfissional());
        enfermeiro.setRegiao(dto.getRegiao());
        enfermeiro.setLaboratorio(LaboratorioDTO.toEntity(dto.getLaboratorio()));
        enfermeiro.setExames(dto.getExames().stream()
                        .map(ExameDTO::toEntity)
                        .collect(Collectors.toList()));

        return enfermeiro;
    }

}
