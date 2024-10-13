package com.starter.spring.service.paciente;

import java.util.*;
import java.util.stream.Collectors;

import com.starter.spring.dto.models.PessoaDTO;
import com.starter.spring.enums.TipoUsuario;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.starter.spring.exceptions.DataIntegrityViolationException;
import com.starter.spring.exceptions.ObjectnotFoundException;
import com.starter.spring.model.Perfil;
import com.starter.spring.model.Pessoa;
import com.starter.spring.repository.PerfilRepository;
import com.starter.spring.repository.PessoaRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PacienteServiceImpl implements PacienteService {

	private final PessoaRepository pessoaRepository;

    private final PerfilRepository perfilRepository;

    @Override
    public PessoaDTO findById(Long id) {
        Optional<Pessoa> obj = pessoaRepository.findById(id);
        return PessoaDTO.toDTO(obj.orElseThrow(() -> new ObjectnotFoundException("Objeto não encontrado! Id: " + id)));
	}

    @Override
    public List<PessoaDTO> findAll(){
        List<Pessoa> pacientes = pessoaRepository.findAll();
        return pacientes.stream()
                        .map(PessoaDTO::toDTO)
                        .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public PessoaDTO create(PessoaDTO objDTO) {
        validateByEmailAndCpf(objDTO);
        Set<Perfil> perfis = getDefaultProfiles();
        objDTO.setSenha(new BCryptPasswordEncoder().encode(objDTO.getSenha()));
        Pessoa paciente = PessoaDTO.toEntity(objDTO);
        paciente.setPerfis(perfis);
        paciente = pessoaRepository.save(paciente);
        return PessoaDTO.toDTO(paciente);
    }

    @Transactional
    @Override
    public PessoaDTO update(Long Id, PessoaDTO objDTO) {
        objDTO.setId(Id);
		validateByEmailAndCpf(objDTO);
        Set<Perfil> perfis = getDefaultProfiles();
        Pessoa paciente = PessoaDTO.toEntity(objDTO);
        paciente.setPerfis(perfis);
        paciente = pessoaRepository.save(paciente);
        return PessoaDTO.toDTO(paciente);
	}

    private void validateByEmailAndCpf(PessoaDTO objDTO) {
        Optional<Pessoa> obj = pessoaRepository.findByCpfOrEmail(objDTO.getCpf(), objDTO.getEmail());
        if (obj.isPresent()) {
            throw new DataIntegrityViolationException("CPF ou E-mail já cadastrado no sistema!");
        }
    }

    private Set<Perfil> getDefaultProfiles() {
        Set<Perfil> perfis = new HashSet<>();
        Perfil perfil = perfilRepository.findByNome(TipoUsuario.PACIENTE.getDescricao());
        if (perfil != null) {
            perfis.add(perfil);
        }
        return perfis;
    }
}
