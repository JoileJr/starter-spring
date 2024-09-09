package com.starter.spring.service.Enfermeiros;

import com.starter.spring.dto.EnfermeiroDTO;

import java.util.List;

public interface EnfermeiroService {

    EnfermeiroDTO findById(Long id);

    List<EnfermeiroDTO> findAll();

    EnfermeiroDTO create(EnfermeiroDTO objDTO);

    EnfermeiroDTO update(Long Id ,EnfermeiroDTO objDTO);

}
