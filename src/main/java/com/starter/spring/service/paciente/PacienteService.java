package com.starter.spring.service.paciente;

import com.starter.spring.dto.models.PessoaDTO;

import java.util.List;

public interface PacienteService {
    
    PessoaDTO findById(Long id);

    List<PessoaDTO> findAll();

    PessoaDTO create(PessoaDTO objDTO);

    PessoaDTO update(Long Id, PessoaDTO objDTO);
}
