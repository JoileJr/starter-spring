package com.starter.spring.service.paciente;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.starter.spring.dto.PacienteDTO;
import com.starter.spring.exceptions.DataIntegrityViolationException;
import com.starter.spring.exceptions.ObjectnotFoundException;
import com.starter.spring.model.Paciente;
import com.starter.spring.model.Perfil;
import com.starter.spring.model.Pessoa;
import com.starter.spring.repository.PacienteRepository;
import com.starter.spring.repository.PerfilRepository;
import com.starter.spring.repository.PessoaRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PacienteServiceImpl implements PacienteService {

    private final PacienteRepository pacienteRepository;

	private final PessoaRepository pessoaRepository;

    private final PerfilRepository perfilRepository;

    @Override
    public PacienteDTO findById(Long id) {
        Optional<Paciente> obj = pacienteRepository.findById(id);
        return PacienteDTO.toDTO(obj.orElseThrow(() -> new ObjectnotFoundException("Objeto não encontrado! Id: " + id))); 
	}

    @Override
    public List<PacienteDTO> findAll(){
        List<Paciente> pacientes = pacienteRepository.findAll();
        return pacientes.stream()
                        .map(PacienteDTO::toDTO)
                        .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public PacienteDTO create(PacienteDTO objDTO) {
        validateByEmailAndCpf(objDTO);
        Set<Perfil> perfis = getDefaultProfiles();
        Paciente paciente = PacienteDTO.toEntity(objDTO);
        paciente.setPerfis(perfis);
        paciente = pacienteRepository.save(paciente);
        return PacienteDTO.toDTO(paciente);
    }

    @Transactional
    @Override
    public PacienteDTO update(Long Id, PacienteDTO objDTO) {
        objDTO.setId(Id);
		validateByEmailAndCpf(objDTO);
        Set<Perfil> perfis = getDefaultProfiles();
        Paciente paciente = PacienteDTO.toEntity(objDTO);
        paciente.setPerfis(perfis);
        paciente = pacienteRepository.save(paciente);
        return PacienteDTO.toDTO(paciente);
	}

    private void validateByEmailAndCpf(PacienteDTO objDTO) {
        Optional<Pessoa> obj = pessoaRepository.findByCpf(objDTO.getCpf());
        if (obj.isPresent() && obj.get().getCpf().equals(objDTO.getCpf())) {
            throw new DataIntegrityViolationException("CPF já cadastrado no sistema!");
        }

        obj = pessoaRepository.findByEmail(objDTO.getEmail());
        if (obj.isPresent() && obj.get().getEmail().equals(objDTO.getEmail())) {
            throw new DataIntegrityViolationException("E-mail já cadastrado no sistema!");
        }
    }

    private Set<Perfil> getDefaultProfiles() {
        Set<Perfil> perfis = new HashSet<>();
        Perfil perfil = perfilRepository.findByNome("paciente");
        if (perfil != null) {
            perfis.add(perfil);
        }
        return perfis;
    }
}
