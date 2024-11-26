package com.starter.spring.service.funcionarios;

import com.starter.spring.dto.models.ProfissionalSaudeDTO;
import com.starter.spring.dto.useCases.ProfissionalSaudeRequest;

import java.util.List;

public interface FuncionarioService {

    ProfissionalSaudeDTO findById(Long id);

    List<ProfissionalSaudeDTO> findAll();

    ProfissionalSaudeDTO create(ProfissionalSaudeRequest objDTO);

    ProfissionalSaudeDTO update(Long Id , ProfissionalSaudeRequest objDTO);

}
