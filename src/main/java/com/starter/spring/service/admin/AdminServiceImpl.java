package com.starter.spring.service.admin;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.starter.spring.dto.models.AdministrativoDTO;
import com.starter.spring.exceptions.DataIntegrityViolationException;
import com.starter.spring.exceptions.ObjectnotFoundException;
import com.starter.spring.model.Administrativo;
import com.starter.spring.model.Perfil;
import com.starter.spring.model.Pessoa;
import com.starter.spring.repository.AdministrativoRepository;
import com.starter.spring.repository.PerfilRepository;
import com.starter.spring.repository.PessoaRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdministrativoRepository administrativoRepository;

    private final PessoaRepository pessoaRepository;

    private final PerfilRepository perfilRepository;

    @Override
    public AdministrativoDTO findById(Long id) {
        Optional<Administrativo> obj = administrativoRepository.findById(id);
        return AdministrativoDTO
                .toDTO(obj.orElseThrow(() -> new ObjectnotFoundException("Objeto não encontrado! Id: " + id)));
    }

    @Override
    public List<AdministrativoDTO> findAll() {
        List<Administrativo> administrativo = administrativoRepository.findAll();
        return administrativo.stream()
                .map(AdministrativoDTO::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public AdministrativoDTO create(AdministrativoDTO objDTO) {
        validateByEmailAndCpf(objDTO);
        Set<Perfil> perfis = getDefaultProfiles();
        objDTO.setSenha(new BCryptPasswordEncoder().encode(objDTO.getSenha()));
        Administrativo administrativo = AdministrativoDTO.toEntity(objDTO);
        administrativo.setPerfis(perfis);
        administrativo = administrativoRepository.save(administrativo);
        return AdministrativoDTO.toDTO(administrativo);
    }

    @Transactional
    @Override
    public AdministrativoDTO update(Long Id, AdministrativoDTO objDTO) {
        objDTO.setId(Id);
        Set<Perfil> perfis = getDefaultProfiles();
        Administrativo administrativo = AdministrativoDTO.toEntity(objDTO);
        administrativo.setPerfis(perfis);
        administrativo = administrativoRepository.save(administrativo);
        return AdministrativoDTO.toDTO(administrativo);
    }

    private void validateByEmailAndCpf(AdministrativoDTO objDTO) {
        Optional<Pessoa> obj = pessoaRepository.findByCpfOrEmail(objDTO.getCpf(), objDTO.getEmail());
        if (obj.isPresent()) {
            throw new DataIntegrityViolationException("CPF ou E-mail já cadastrado no sistema!");
        }
    }

    private Set<Perfil> getDefaultProfiles() {
        Set<Perfil> perfis = new HashSet<>();
        List<Perfil> perfisList = perfilRepository.findByNomeIn(Arrays.asList("paciente", "administrativo"));

        if (!perfisList.isEmpty()) {
            perfis.addAll(perfisList);
        }
        return perfis;
    }
    
}
