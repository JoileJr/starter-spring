package com.starter.spring.service.admin;

import java.util.List;

import com.starter.spring.dto.models.AdministrativoDTO;

public interface AdminService {
    
    AdministrativoDTO findById(Long id);

    List<AdministrativoDTO> findAll();

    AdministrativoDTO create(AdministrativoDTO objDTO);

    AdministrativoDTO update(Long Id, AdministrativoDTO objDTO);
}
