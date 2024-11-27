package com.starter.spring.service.paciente;

import com.starter.spring.dto.models.PessoaDTO;
import com.starter.spring.dto.useCases.FilterPersonsRequest;

import java.util.List;

public interface PacienteService {
    
    PessoaDTO findById(Long id);

    PessoaDTO findByCpf(String cpf);

    List<PessoaDTO> findAll();

    PessoaDTO create(PessoaDTO objDTO);

    PessoaDTO update(Long Id, PessoaDTO objDTO);

    List<PessoaDTO> findByFilter(FilterPersonsRequest obj);
}
