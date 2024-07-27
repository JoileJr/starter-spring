package com.starter.spring.infra.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.starter.spring.application.EnderecoUseCase;
import com.starter.spring.domain.Endereco;

@Service
public class EnderecoService {

    private final Map<String, EnderecoUseCase> enderecoUseCaseMap;
    private final EnderecoUseCase enderecoUseCase;

    public EnderecoService(Map<String, EnderecoUseCase> enderecoUseCaseMap, EnderecoUseCase enderecoUseCase) {
        this.enderecoUseCaseMap = enderecoUseCaseMap;
        this.enderecoUseCase = enderecoUseCase;
    }

    public Endereco findById(Long id){
        return enderecoUseCase.findById(id);
    }
		
    public List<Endereco> list(){
        return enderecoUseCase.list();
    }

    public Endereco create(Endereco objDTO){
        return enderecoUseCase.create(objDTO);
    }
 
	public Endereco update(Long id, Endereco objDTO){
        return enderecoUseCase.update(id, objDTO);
    }
	
    public void delete(Long id){
        enderecoUseCase.delete(id);
    }

}
