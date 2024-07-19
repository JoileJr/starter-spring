package com.starter.spring.service.Endereco;

import java.util.List;

import com.starter.spring.dto.EnderecoDTO;

public interface EnderecoService {

    EnderecoDTO findById(Long id);
		
    List<EnderecoDTO> list();

    EnderecoDTO create(EnderecoDTO objDTO);
 
	EnderecoDTO update(Long id, EnderecoDTO objDTO);
	
    void delete(Long id);

}
