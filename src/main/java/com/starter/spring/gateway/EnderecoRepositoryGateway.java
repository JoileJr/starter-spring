package com.starter.spring.gateway;

import java.util.List;

import com.starter.spring.domain.Endereco;

public interface EnderecoRepositoryGateway {
    Endereco findById(Long id);
		
    List<Endereco> list();

    Endereco create(Endereco objDTO);
 
	Endereco update(Long id, Endereco objDTO);
	
    void delete(Long id);
}
