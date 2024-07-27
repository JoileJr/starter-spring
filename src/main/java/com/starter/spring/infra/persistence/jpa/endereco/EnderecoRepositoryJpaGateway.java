package com.starter.spring.infra.persistence.jpa.endereco;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.starter.spring.domain.Endereco;
import com.starter.spring.exceptions.ObjectnotFoundException;
import com.starter.spring.gateway.EnderecoRepositoryGateway;
import com.starter.spring.mapper.MapperEndereco;

@Component
public class EnderecoRepositoryJpaGateway implements EnderecoRepositoryGateway {

    private final EnderecoRepository enderecoRepository;
    private final MapperEndereco mapperEndereco;

    public EnderecoRepositoryJpaGateway(EnderecoRepository repository, MapperEndereco mapper) {
        this.enderecoRepository = repository;
        this.mapperEndereco = mapper;
    }

    @Override
    public Endereco findById(Long id) {
        Optional<EnderecoEntity> obj = enderecoRepository.findById(id);
		return mapperEndereco.toDomain(obj.orElseThrow(() -> new ObjectnotFoundException("Objeto não encontrado! Id: " + id)));
    }

    @Override
    public List<Endereco> list() {
        List<EnderecoEntity> objs = enderecoRepository.findAll();
        return mapperEndereco.toListDomain(objs);
    }

    @Override
    public Endereco create(Endereco objDTO) {
        EnderecoEntity obj = enderecoRepository.save(mapperEndereco.toEntity(objDTO));
        Endereco newDto = mapperEndereco.toDomain(obj);
        return newDto;
    }

    @Override
    public Endereco update(Long id, Endereco objDTO) {
        EnderecoEntity obj = enderecoRepository.save(mapperEndereco.toEntity(objDTO));
        Endereco newDto = mapperEndereco.toDomain(obj);
        return newDto;
    }

    @Override
    public void delete(Long id) {
        Endereco obj = findById(id);
        enderecoRepository.deleteById(obj.id());
    }
    
}
