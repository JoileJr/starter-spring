package com.starter.spring.service.funcionarios;

import com.starter.spring.dto.models.ProfissionalSaudeDTO;

import java.util.List;

public interface FuncionarioService {

    ProfissionalSaudeDTO findById(Long id);

    List<ProfissionalSaudeDTO> findAll();

    ProfissionalSaudeDTO create(ProfissionalSaudeDTO objDTO);

    ProfissionalSaudeDTO update(Long Id , ProfissionalSaudeDTO objDTO);

}
