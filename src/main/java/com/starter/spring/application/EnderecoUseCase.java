package com.starter.spring.application;

import java.util.List;

import org.springframework.stereotype.Component;

import com.starter.spring.domain.Endereco;
import com.starter.spring.gateway.EnderecoRepositoryGateway;

@Component
public class EnderecoUseCase {

    private final EnderecoRepositoryGateway enderecoGateway;

    public EnderecoUseCase(EnderecoRepositoryGateway gateway) {
        this.enderecoGateway = gateway;
    }

    public Endereco findById(Long id){
        return enderecoGateway.findById(id);
    }
		
    public List<Endereco> list(){
        return enderecoGateway.list();
    }

    public Endereco create(Endereco objDTO){
        return enderecoGateway.create(objDTO);
    }
 
	public Endereco update(Long id, Endereco objDTO){
        return enderecoGateway.update(id, objDTO);
    }
	
    public void delete(Long id){
        enderecoGateway.delete(id);
    }

}