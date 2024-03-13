package com.starter.spring.service.firstEntity;

import com.starter.spring.dto.FirstEntityDTO;
import com.starter.spring.mapper.MapperFirstEntity;
import com.starter.spring.model.FirstEntity;
import com.starter.spring.repository.FirstEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FirstEntityServiceImpl implements FirstEntityService {

    private final FirstEntityRepository repository;

    private final MapperFirstEntity mapper;

    @Override
    public FirstEntityDTO save(FirstEntityDTO dto) {
        FirstEntity firstEntity = repository.save(mapper.toEntity(dto));
        return mapper.toDto(firstEntity);
    }

    @Override
    public List<FirstEntityDTO> list() {
        List<FirstEntity> firstEntityList = repository.findAll();
        return mapper.toListDto(firstEntityList);
    }

}
