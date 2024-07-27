package com.starter.spring.infra.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.starter.spring.application.PessoaUseCase;
import com.starter.spring.domain.Pessoa;

@Service
public class PessoaService {

    private final Map<String, PessoaUseCase> pessoaUseCaseMap;

    private final PessoaUseCase pessoaUseCase;

    public PessoaService(Map<String, PessoaUseCase> pessoaUseCaseMap, PessoaUseCase pessoaUseCase) {
        this.pessoaUseCaseMap = pessoaUseCaseMap;
        this.pessoaUseCase = pessoaUseCase;
    }

    public Pessoa findById(Long id){
        return pessoaUseCase.findById(id);
    }
		
    public List<Pessoa> list(){
        return pessoaUseCase.list();
    }

    public Pessoa create(Pessoa objDTO){
        return pessoaUseCase.create(objDTO);
    }
 
	public Pessoa update(Long id, Pessoa objDTO){
        return pessoaUseCase.update(id, objDTO);
    }
	
    public void delete(Long id){
        pessoaUseCase.delete(id);
    }

    public void validaPorEmail(Pessoa objDTO){
        pessoaUseCase.validaPorEmail(objDTO);
    }
    
}
