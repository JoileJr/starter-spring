package com.starter.spring.service.Paciente;

import java.util.List;

import com.starter.spring.dto.PacienteDTO;

public interface PacienteService {
    
    public PacienteDTO findById(Long id);

    public List<PacienteDTO> findAll();

    public PacienteDTO create(PacienteDTO objDTO);

    public PacienteDTO update(Long Id ,PacienteDTO objDTO);
}
