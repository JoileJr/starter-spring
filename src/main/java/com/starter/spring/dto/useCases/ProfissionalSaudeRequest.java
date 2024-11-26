package com.starter.spring.dto.useCases;

import java.util.Date;

import com.starter.spring.dto.models.LaboratorioDTO;
import com.starter.spring.enums.TipoUsuario;
import com.starter.spring.model.ProfissionalSaude;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProfissionalSaudeRequest {

    private Long id;

    @NotNull(message = "O campo nome é requerido")
    private String nome;

    @NotNull(message = "O campo cpf é requerido")
    private String cpf;

    @NotNull(message = "O campo telefone é requerido")
    private String telefone;

    @NotNull(message = "O campo sexo é requerido")
    private String sexo;

    @NotNull(message = "O campo email é requerido")
    private String email;

    private String senha;

    @NotNull(message = "O campo dataNascimento é requerido")
    private Date dataNascimento;

    @NotNull(message = "O perfil é obrigatório")
    private String perfis;

    @NotNull(message = "O registro profissional é requerido")
    private String registroProfissional;

    @NotNull(message = "O tipo de profissional é requerido")
    private String tipoProfissional;

    private String regiao;

    private LaboratorioDTO laboratorio;

    public static ProfissionalSaude toEntity(ProfissionalSaudeRequest dto) {
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
        enfermeiro.setRegistroProfissional(dto.getRegistroProfissional());
        enfermeiro.setTipoProfissional(dto.getTipoProfissional());
        enfermeiro.setRegiao(dto.getRegiao());
        enfermeiro.setLaboratorio(LaboratorioDTO.toEntity(dto.getLaboratorio()));

        return enfermeiro;
    }
}
