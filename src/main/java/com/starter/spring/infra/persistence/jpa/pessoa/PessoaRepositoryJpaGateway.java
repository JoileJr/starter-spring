package com.starter.spring.infra.persistence.jpa.pessoa;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.starter.spring.domain.Pessoa;
import com.starter.spring.exceptions.DataIntegrityViolationException;
import com.starter.spring.exceptions.ObjectnotFoundException;
import com.starter.spring.gateway.PessoaRepositoryGateway;
import com.starter.spring.infra.persistence.jpa.endereco.EnderecoRepository;
import com.starter.spring.mapper.MapperEndereco;
import com.starter.spring.mapper.MapperPessoa;

import jakarta.validation.Valid;

@Component
public class PessoaRepositoryJpaGateway implements PessoaRepositoryGateway {

    private final PessoaRepository repository;
    private final EnderecoRepository enderecoRepository;

    private final MapperPessoa mapper;
    private final MapperEndereco mapperEndereco;

    public PessoaRepositoryJpaGateway(
        PessoaRepository repository, 
        EnderecoRepository enderecoRepository,
        MapperPessoa mapper,
        MapperEndereco mapperEndereco) {
        this.repository = repository;
        this.enderecoRepository = enderecoRepository;
        this.mapper = mapper;
        this.mapperEndereco = mapperEndereco;
    }

    @Override
    public Pessoa findById(Long id) {
		Optional<PessoaEntity> obj = repository.findById(id);
		return mapper.toDomain(obj.orElseThrow(() -> new ObjectnotFoundException("Objeto não encontrado! Id: " + id)));
	}

    @Override
    public List<Pessoa> list() {
        List<PessoaEntity> list = repository.findAll();
        return mapper.toListDomain(list);
    }

    @Override
    public Pessoa create(Pessoa objDTO) {
        validaPorEmail(objDTO);
        enderecoRepository.save(mapperEndereco.toEntity(objDTO.endereco()));
		PessoaEntity obj = repository.save(mapper.toEntity(objDTO));
        Pessoa newDto = mapper.toDomain(obj);
        return newDto;
	}
 
    @Override
	public Pessoa update(Long id, @Valid Pessoa objDTO) {
        validaPorEmail(objDTO);
		Pessoa oldObj = findById(id);
        enderecoRepository.save(objDTO.endereco().toEntity());
        oldObj = mapper.toDomain(repository.save(oldObj.toEntidade()));
		return oldObj;
	}

    @Override
    public void delete(Long id) {
		Pessoa obj = findById(id);
		repository.deleteById(obj.id());
	}

    @Override
    public void validaPorEmail(Pessoa objDTO) {
        Optional<PessoaEntity> obj = repository.findByEmail(objDTO.email());
        if (obj.isPresent() && obj.get().getId() != objDTO.id()) {
            throw new DataIntegrityViolationException("E-mail já cadastrado no sistema!");
        }
    }
    
}
