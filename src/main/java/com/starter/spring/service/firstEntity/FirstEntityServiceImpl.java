package com.starter.spring.service.firstEntity;

import com.starter.spring.dto.FirstEntityDTO;
import com.starter.spring.mapper.MapperFirstEntity;
import com.starter.spring.model.FirstEntity;
import com.starter.spring.repository.FirstEntityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class FirstEntityServiceImpl implements FirstEntityService {

    @Autowired
    private FirstEntityRepository repository;

    @Autowired
    private MapperFirstEntity mapper;

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
