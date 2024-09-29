package com.starter.spring.service.laboratorio;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.starter.spring.dto.models.EnderecoDTO;
import com.starter.spring.dto.models.LaboratorioDTO;
import com.starter.spring.exceptions.DataIntegrityViolationException;
import com.starter.spring.exceptions.ObjectnotFoundException;
import com.starter.spring.model.Endereco;
import com.starter.spring.model.Laboratorio;
import com.starter.spring.repository.EnderecoRepository;
import com.starter.spring.repository.LaboratorioRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LaboratorioServiceImpl implements LaboratorioService {

    private final LaboratorioRepository laboratorioRepository;

    private final EnderecoRepository enderecoRepository;

    @Override
    public LaboratorioDTO findById(Long id) {
        Optional<Laboratorio> obj = laboratorioRepository.findById(id);
        return LaboratorioDTO
                .toDTO(obj.orElseThrow(() -> new ObjectnotFoundException("Objeto não encontrado! Id: " + id)));
    }

    @Override
    public List<LaboratorioDTO> findAll() {
        List<Laboratorio> laboratorios = laboratorioRepository.findAll();
        return laboratorios.stream()
                .map(LaboratorioDTO::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public LaboratorioDTO create(LaboratorioDTO objDTO) {
        validateByEmailAndCpf(objDTO);
        objDTO.setEndereco(registerAddress(objDTO.getEndereco()));
        Laboratorio laboratorio = LaboratorioDTO.toEntity(objDTO);
        laboratorio = laboratorioRepository.save(laboratorio);
        return LaboratorioDTO.toDTO(laboratorio);
    }

    @Transactional
    @Override
    public LaboratorioDTO update(Long Id, LaboratorioDTO objDTO) {
        objDTO.setId(Id);
        objDTO.setEndereco(registerAddress(objDTO.getEndereco()));
        Laboratorio laboratorio = LaboratorioDTO.toEntity(objDTO);
        laboratorio = laboratorioRepository.save(laboratorio);
        return LaboratorioDTO.toDTO(laboratorio);
    }

    private void validateByEmailAndCpf(LaboratorioDTO objDTO) {
        Optional<Laboratorio> obj = laboratorioRepository.findByCnpj(objDTO.getCnpj());
        if (obj.isPresent() && obj.get().getCnpj().equals(objDTO.getCnpj())) {
            throw new DataIntegrityViolationException("CNPJ já cadastrado no sistema!");
        }
    }

    private EnderecoDTO registerAddress(EnderecoDTO enderecoDTO) {
        if (enderecoDTO == null) {
            throw new DataIntegrityViolationException("Endereço é obrigatorio");
        }
        Endereco endereco = enderecoRepository.save(EnderecoDTO.toEntity(enderecoDTO));
        return EnderecoDTO.toDTO(endereco);
    }
    
}
