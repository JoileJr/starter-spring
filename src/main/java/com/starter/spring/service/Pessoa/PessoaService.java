package com.starter.spring.service.Pessoa;

import com.starter.spring.dto.PessoaDTO;
import java.util.List;

public interface PessoaService {

    PessoaDTO findById(Long id);
		
    List<PessoaDTO> list();

    PessoaDTO create(PessoaDTO objDTO);
 
	PessoaDTO update(Long id, PessoaDTO objDTO);
	
    void delete(Long id);

    void validaPorEmail(PessoaDTO objDTO);   

}