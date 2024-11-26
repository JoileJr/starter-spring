package com.starter.spring.service.funcionarios;

import com.starter.spring.dto.models.ProfissionalSaudeDTO;
import com.starter.spring.dto.useCases.ProfissionalSaudeRequest;
import com.starter.spring.enums.TipoUsuario;
import com.starter.spring.exceptions.DataIntegrityViolationException;
import com.starter.spring.exceptions.ObjectnotFoundException;
import com.starter.spring.model.ProfissionalSaude;
import com.starter.spring.model.Perfil;
import com.starter.spring.model.Pessoa;
import com.starter.spring.repository.EnfermeiroRepository;
import com.starter.spring.repository.PerfilRepository;
import com.starter.spring.repository.PessoaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FuncionarioServiceImpl implements FuncionarioService {

    private final EnfermeiroRepository enfermeiroRepository;

    private final PessoaRepository pessoaRepository;

    private final PerfilRepository perfilRepository;

    @Override
    public ProfissionalSaudeDTO findById(Long id) {
        Optional<ProfissionalSaude> obj = enfermeiroRepository.findById(id);
        return ProfissionalSaudeDTO
                .toDTO(obj.orElseThrow(() -> new ObjectnotFoundException("Objeto não encontrado! Id: " + id)));
    }

    @Override
    public List<ProfissionalSaudeDTO> findAll() {
        List<ProfissionalSaude> enfermeiros = enfermeiroRepository.findAll();
        return enfermeiros.stream()
                .map(ProfissionalSaudeDTO::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public ProfissionalSaudeDTO create(ProfissionalSaudeRequest objDTO) {
        validateByEmailAndCpf(objDTO);
        Set<Perfil> perfis = getDefaultProfiles(objDTO.getPerfis());
        ProfissionalSaude enfermeiro = ProfissionalSaudeRequest.toEntity(objDTO);
        enfermeiro.setPerfis(perfis);
        enfermeiro = enfermeiroRepository.save(enfermeiro);
        return ProfissionalSaudeDTO.toDTO(enfermeiro);
    }

    @Transactional
    @Override
    public ProfissionalSaudeDTO update(Long Id, ProfissionalSaudeRequest objDTO) {
        objDTO.setId(Id);
        Set<Perfil> perfis = getDefaultProfiles(objDTO.getPerfis());
        ProfissionalSaude enfermeiro = ProfissionalSaudeRequest.toEntity(objDTO);
        enfermeiro.setPerfis(perfis);
        enfermeiro = enfermeiroRepository.save(enfermeiro);
        return ProfissionalSaudeDTO.toDTO(enfermeiro);
    }

    private void validateByEmailAndCpf(ProfissionalSaudeRequest objDTO) {
        Optional<Pessoa> obj = pessoaRepository.findByCpfOrEmail(objDTO.getCpf(), objDTO.getEmail());
        if (obj.isPresent()) {
            throw new DataIntegrityViolationException("CPF ou E-mail já cadastrado no sistema!");
        }
    }

    private Set<Perfil> getDefaultProfiles(String perfisBuscar) {
        Set<Perfil> perfis = new HashSet<>();
        Perfil perfil;
        if (perfisBuscar == TipoUsuario.BIOMEDICO.getDescricao()) {
            perfil = perfilRepository.findByNome(TipoUsuario.BIOMEDICO.getDescricao());
        } else if (perfisBuscar == TipoUsuario.ENFERMEIRO.getDescricao()) {
            perfil = perfilRepository.findByNome(TipoUsuario.ENFERMEIRO.getDescricao());
        } else if (perfisBuscar == TipoUsuario.TECNICO_ENFERMAGEM.getDescricao()) {
            perfil = perfilRepository.findByNome(TipoUsuario.TECNICO_ENFERMAGEM.getDescricao());
        } else if (perfisBuscar == TipoUsuario.MEDICO.getDescricao()) {
            perfil = perfilRepository.findByNome(TipoUsuario.MEDICO.getDescricao());
        } else {
            perfil = perfilRepository.findByNome(TipoUsuario.PACIENTE.getDescricao());
        }
        if (perfil != null) {
            perfis.add(perfil);
        }
        return perfis;
    }

}
