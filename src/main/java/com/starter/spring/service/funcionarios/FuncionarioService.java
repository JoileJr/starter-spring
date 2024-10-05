package com.starter.spring.service.funcionarios;

import com.starter.spring.dto.models.EnfermeiroDTO;

import java.util.List;

public interface FuncionarioService {

    EnfermeiroDTO findById(Long id);

    List<EnfermeiroDTO> findAll();

    EnfermeiroDTO create(EnfermeiroDTO objDTO);

    EnfermeiroDTO update(Long Id ,EnfermeiroDTO objDTO);

}
