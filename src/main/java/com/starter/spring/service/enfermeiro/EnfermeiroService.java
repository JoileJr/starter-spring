package com.starter.spring.service.enfermeiro;

import com.starter.spring.dto.models.EnfermeiroDTO;

import java.util.List;

public interface EnfermeiroService {

    EnfermeiroDTO findById(Long id);

    List<EnfermeiroDTO> findAll();

    EnfermeiroDTO create(EnfermeiroDTO objDTO);

    EnfermeiroDTO update(Long Id ,EnfermeiroDTO objDTO);

}
