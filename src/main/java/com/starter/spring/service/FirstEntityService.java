package com.starter.spring.service;

import com.starter.spring.dto.FirstEntityDTO;
import com.starter.spring.exceptions.DataIntegrityViolationException;
import com.starter.spring.exceptions.ObjectnotFoundException;
import com.starter.spring.mapper.MapperFirstEntity;
import com.starter.spring.model.FirstEntity;
import com.starter.spring.repository.FirstEntityRepository;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FirstEntityService {

    private final FirstEntityRepository repository;

    private final MapperFirstEntity mapper;

    public FirstEntityDTO findById(Long id) {
		Optional<FirstEntity> obj = repository.findById(id);
		return mapper.toDto(obj.orElseThrow(() -> new ObjectnotFoundException("Objeto não encontrado! Id: " + id)));
	}

    public List<FirstEntityDTO> list() {
        List<FirstEntity> firstEntityList = repository.findAll();
        return mapper.toListDto(firstEntityList);
    }

    public FirstEntityDTO create(FirstEntityDTO objDTO) {
        validaPorEmail(objDTO);
		FirstEntity firstEntity = repository.save(mapper.toEntity(objDTO));
        FirstEntityDTO firstEntityDTO = mapper.toDto(firstEntity);
        return firstEntityDTO;
	}
 
	public FirstEntityDTO update(Long id, @Valid FirstEntityDTO objDTO) {
        validaPorEmail(objDTO);
		objDTO.setId(id);
		FirstEntityDTO oldObj = findById(id);
        oldObj = mapper.toDto(repository.save(mapper.toEntity(objDTO)));
		return oldObj;
	}

    public void delete(Long id) {
		FirstEntityDTO obj = findById(id);
		repository.deleteById(obj.getId());
	}

    private void validaPorEmail(FirstEntityDTO objDTO) {
         Optional<FirstEntity> obj = repository.findByEmail(objDTO.getEmail());
        if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
            throw new DataIntegrityViolationException("E-mail já cadastrado no sistema!");
        }
    }

}
