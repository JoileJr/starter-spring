package com.starter.spring.service.paciente;

import java.util.List;

import com.starter.spring.dto.models.PacienteDTO;

public interface PacienteService {
    
    PacienteDTO findById(Long id);

    List<PacienteDTO> findAll();

    PacienteDTO create(PacienteDTO objDTO);

    PacienteDTO update(Long Id ,PacienteDTO objDTO);
}
