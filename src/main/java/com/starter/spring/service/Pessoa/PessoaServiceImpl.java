package com.starter.spring.service.Pessoa;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.starter.spring.dto.PessoaDTO;
import com.starter.spring.exceptions.DataIntegrityViolationException;
import com.starter.spring.exceptions.ObjectnotFoundException;
import com.starter.spring.mapper.MapperPessoa;
import com.starter.spring.model.Pessoa;
import com.starter.spring.repository.PessoaRepository;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PessoaServiceImpl implements PessoaService {

    private final PessoaRepository repository;

    private final MapperPessoa mapper;

    @Override
    public PessoaDTO findById(Long id) {
		Optional<Pessoa> obj = repository.findById(id);
		return mapper.toDto(obj.orElseThrow(() -> new ObjectnotFoundException("Objeto não encontrado! Id: " + id)));
	}

    @Override
    public List<PessoaDTO> list() {
        List<Pessoa> list = repository.findAll();
        return mapper.toListDto(list);
    }

    @Override
    public PessoaDTO create(PessoaDTO objDTO) {
        validaPorEmail(objDTO);
		Pessoa obj = repository.save(mapper.toEntity(objDTO));
        PessoaDTO newDto = mapper.toDto(obj);
        return newDto;
	}
 
    @Override
	public PessoaDTO update(Long id, @Valid PessoaDTO objDTO) {
        validaPorEmail(objDTO);
		objDTO.setId(id);
		PessoaDTO oldObj = findById(id);
        oldObj = mapper.toDto(repository.save(mapper.toEntity(objDTO)));
		return oldObj;
	}

    @Override
    public void delete(Long id) {
		PessoaDTO obj = findById(id);
		repository.deleteById(obj.getId());
	}

    @Override
    public void validaPorEmail(PessoaDTO objDTO) {
         Optional<Pessoa> obj = repository.findByEmail(objDTO.getEmail());
        if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
            throw new DataIntegrityViolationException("E-mail já cadastrado no sistema!");
        }
    }
}
