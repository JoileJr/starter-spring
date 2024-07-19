package com.starter.spring.service.Endereco;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.starter.spring.dto.EnderecoDTO;
import com.starter.spring.exceptions.ObjectnotFoundException;
import com.starter.spring.mapper.MapperEndereco;
import com.starter.spring.model.Endereco;
import com.starter.spring.repository.EnderecoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EnderecoServiceImpl implements EnderecoService {

    private final EnderecoRepository repository;
    
    private final MapperEndereco mapper;

    @Override
    public EnderecoDTO findById(Long id) {
        Optional<Endereco> obj = repository.findById(id);
		return mapper.toDto(obj.orElseThrow(() -> new ObjectnotFoundException("Objeto não encontrado! Id: " + id)));
    }

    @Override
    public List<EnderecoDTO> list() {
        List<Endereco> firstEntityList = repository.findAll();
        return mapper.toListDto(firstEntityList);
    }

    @Override
    public EnderecoDTO create(EnderecoDTO objDTO) {
        Endereco obj = repository.save(mapper.toEntity(objDTO));
        EnderecoDTO newDto = mapper.toDto(obj);
        return newDto;
    }

    @Override
    public EnderecoDTO update(Long id, EnderecoDTO objDTO) {
        objDTO.setId(id);
        EnderecoDTO oldObj = findById(id);
        oldObj = mapper.toDto(repository.save(mapper.toEntity(objDTO)));
        return oldObj;
    }
    

    @Override
    public void delete(Long id) {
        EnderecoDTO obj = findById(id);
        repository.deleteById(obj.getId());
    }
    
}
