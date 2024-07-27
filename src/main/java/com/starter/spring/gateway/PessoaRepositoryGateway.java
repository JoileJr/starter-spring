package com.starter.spring.gateway;

import java.util.List;

import com.starter.spring.domain.Pessoa;

public interface PessoaRepositoryGateway {
    Pessoa findById(Long id);
		
    List<Pessoa> list();

    Pessoa create(Pessoa objDTO);
 
	Pessoa update(Long id, Pessoa objDTO);
	
    void delete(Long id);

    void validaPorEmail(Pessoa objDTO);   
}
