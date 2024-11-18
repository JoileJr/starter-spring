package com.starter.spring.service.laboratorio;

import java.util.List;

import com.starter.spring.dto.models.LaboratorioDTO;
import com.starter.spring.dto.useCases.LaboratorioCreateRequest;

public interface LaboratorioService {
    
    LaboratorioDTO findById(Long id);

    List<LaboratorioDTO> findAll();

    LaboratorioDTO create(LaboratorioCreateRequest objDTO);

    LaboratorioDTO update(Long Id, LaboratorioDTO objDTO);

}
