package com.starter.spring.service.laboratorio;

import java.util.List;

import com.starter.spring.dto.models.LaboratorioDTO;

public interface LaboratorioService {
    
    LaboratorioDTO findById(Long id);

    List<LaboratorioDTO> findAll();

    LaboratorioDTO create(LaboratorioDTO objDTO);

    LaboratorioDTO update(Long Id, LaboratorioDTO objDTO);

}
