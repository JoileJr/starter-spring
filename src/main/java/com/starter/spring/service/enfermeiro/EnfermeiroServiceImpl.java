package com.starter.spring.service.enfermeiro;

import com.starter.spring.dto.EnfermeiroDTO;
import com.starter.spring.exceptions.DataIntegrityViolationException;
import com.starter.spring.exceptions.ObjectnotFoundException;
import com.starter.spring.model.Enfermeiro;
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
public class EnfermeiroServiceImpl implements EnfermeiroService {

    private final EnfermeiroRepository enfermeiroRepository;

    private final PessoaRepository pessoaRepository;

    private final PerfilRepository perfilRepository;

    @Override
    public EnfermeiroDTO findById(Long id) {
        Optional<Enfermeiro> obj = enfermeiroRepository.findById(id);
        return EnfermeiroDTO.toDTO(obj.orElseThrow(() -> new ObjectnotFoundException("Objeto não encontrado! Id: " + id)));
    }

    @Override
    public List<EnfermeiroDTO> findAll() {
        List<Enfermeiro> enfermeiros = enfermeiroRepository.findAll();
        return enfermeiros.stream()
                .map(EnfermeiroDTO::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public EnfermeiroDTO create(EnfermeiroDTO objDTO) {
        validateByEmailAndCpf(objDTO);
        Set<Perfil> perfis = getDefaultProfiles();
        Enfermeiro enfermeiro = EnfermeiroDTO.toEntity(objDTO);
        enfermeiro.setPerfis(perfis);
        enfermeiro = enfermeiroRepository.save(enfermeiro);
        return EnfermeiroDTO.toDTO(enfermeiro);
    }

    @Transactional
    @Override
    public EnfermeiroDTO update(Long Id, EnfermeiroDTO objDTO) {
        objDTO.setId(Id);
        Set<Perfil> perfis = getDefaultProfiles();
        Enfermeiro enfermeiro = EnfermeiroDTO.toEntity(objDTO);
        enfermeiro.setPerfis(perfis);
        enfermeiro = enfermeiroRepository.save(enfermeiro);
        return EnfermeiroDTO.toDTO(enfermeiro);
    }

    private void validateByEmailAndCpf(EnfermeiroDTO objDTO) {
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
        List<Perfil> perfisList = perfilRepository.findByNomeIn(Arrays.asList("paciente", "enfermeiro"));

        if (!perfisList.isEmpty()) {
            perfis.addAll(perfisList);
        }
        return perfis;
    }
}
