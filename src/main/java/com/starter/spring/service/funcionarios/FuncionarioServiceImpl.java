package com.starter.spring.service.funcionarios;

import com.starter.spring.dto.models.PerfilDTO;
import com.starter.spring.dto.models.ProfissionalSaudeDTO;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
        return ProfissionalSaudeDTO.toDTO(obj.orElseThrow(() -> new ObjectnotFoundException("Objeto não encontrado! Id: " + id)));
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
    public ProfissionalSaudeDTO create(ProfissionalSaudeDTO objDTO) {
        validateByEmailAndCpf(objDTO);
        List<String> descricoesPerfis = objDTO.getPerfis().stream()
                .map(PerfilDTO::getNome)
                .toList();
        Set<Perfil> perfis = getDefaultProfiles(descricoesPerfis);
        objDTO.setSenha(new BCryptPasswordEncoder().encode(objDTO.getSenha()));
        ProfissionalSaude enfermeiro = ProfissionalSaudeDTO.toEntity(objDTO);
        enfermeiro.setPerfis(perfis);
        enfermeiro = enfermeiroRepository.save(enfermeiro);
        return ProfissionalSaudeDTO.toDTO(enfermeiro);
    }

    @Transactional
    @Override
    public ProfissionalSaudeDTO update(Long Id, ProfissionalSaudeDTO objDTO) {
        objDTO.setId(Id);
        // alterar forma de receber roles
        Set<Perfil> perfis = getDefaultProfiles(Arrays.asList(TipoUsuario.PACIENTE.getDescricao()));
        ProfissionalSaude enfermeiro = ProfissionalSaudeDTO.toEntity(objDTO);
        enfermeiro.setPerfis(perfis);
        enfermeiro = enfermeiroRepository.save(enfermeiro);
        return ProfissionalSaudeDTO.toDTO(enfermeiro);
    }

    private void validateByEmailAndCpf(ProfissionalSaudeDTO objDTO) {
        Optional<Pessoa> obj = pessoaRepository.findByCpfOrEmail(objDTO.getCpf(), objDTO.getEmail());
        if (obj.isPresent()) {
            throw new DataIntegrityViolationException("CPF ou E-mail já cadastrado no sistema!");
        }
    }

    private Set<Perfil> getDefaultProfiles(List<String> perfisBuscar) {
        Set<Perfil> perfis = new HashSet<>();
        List<Perfil> perfisList = perfilRepository.findByNomeIn(perfisBuscar);

        if (!perfisList.isEmpty()) {
            perfis.addAll(perfisList);
        }
        return perfis;
    }
}
