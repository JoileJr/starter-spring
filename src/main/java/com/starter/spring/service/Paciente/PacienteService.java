package com.starter.spring.service.Paciente;

import java.util.List;

import com.starter.spring.dto.PacienteDTO;

public interface PacienteService {
    
    PacienteDTO findById(Long id);

    List<PacienteDTO> findAll();

    PacienteDTO create(PacienteDTO objDTO);

    PacienteDTO update(Long Id ,PacienteDTO objDTO);
}
