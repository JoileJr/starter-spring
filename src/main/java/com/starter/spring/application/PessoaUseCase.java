package com.starter.spring.application;

import java.util.List;

import org.springframework.stereotype.Component;

import com.starter.spring.domain.Pessoa;
import com.starter.spring.gateway.PessoaRepositoryGateway;

@Component
public class PessoaUseCase {

    private final PessoaRepositoryGateway pessoaGateway;

    public PessoaUseCase(PessoaRepositoryGateway gateway) {
        this.pessoaGateway = gateway;
    }

    public Pessoa findById(Long id){
        return pessoaGateway.findById(id);
    }
		
    public List<Pessoa> list(){
        return pessoaGateway.list();
    }

    public Pessoa create(Pessoa objDTO){
        return pessoaGateway.create(objDTO);
    }
 
	public Pessoa update(Long id, Pessoa objDTO){
        return pessoaGateway.update(id, objDTO);
    }
	
    public void delete(Long id){
        pessoaGateway.delete(id);
    }

    public void validaPorEmail(Pessoa objDTO){
        pessoaGateway.validaPorEmail(objDTO);
    }
}