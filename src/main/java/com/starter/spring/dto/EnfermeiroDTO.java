package com.starter.spring.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.starter.spring.model.Enfermeiro;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnfermeiroDTO extends PessoaDTO {
    
    @NotNull(message = "O campo coren é requerido")
    private String coren;

    @NotNull(message = "A região é requerida")
    private String regiao;

    private LaboratorioDTO laboratorio;

    private List<ExameDTO> exames;

    public static EnfermeiroDTO toDTO(Enfermeiro enfermeiro) {
        if (enfermeiro == null) {
            return null;
        }

        EnfermeiroDTO dto = new EnfermeiroDTO();
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
        dto.setCoren(enfermeiro.getCoren());
        dto.setRegiao(enfermeiro.getRegiao());
        dto.setLaboratorio(LaboratorioDTO.toDTO(enfermeiro.getLaboratorio()));
        dto.setExames(enfermeiro.getExames().stream()
                        .map(ExameDTO::toDTO)
                        .collect(Collectors.toList()));

        return dto;
    }

    public static Enfermeiro toEntity(EnfermeiroDTO dto) {
        if (dto == null) {
            return null;
        }

        Enfermeiro enfermeiro = new Enfermeiro();
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
        enfermeiro.setCoren(dto.getCoren());
        enfermeiro.setRegiao(dto.getRegiao());
        enfermeiro.setLaboratorio(LaboratorioDTO.toEntity(dto.getLaboratorio()));
        enfermeiro.setExames(dto.getExames().stream()
                        .map(ExameDTO::toEntity)
                        .collect(Collectors.toList()));

        return enfermeiro;
    }

}
