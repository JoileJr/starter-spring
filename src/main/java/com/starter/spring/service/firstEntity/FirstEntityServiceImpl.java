package com.starter.spring.service.firstEntity;

import com.starter.spring.dto.FirstEntityDTO;
import com.starter.spring.dto.InfoDTO;
import com.starter.spring.mapper.MapperFirstEntity;
import com.starter.spring.model.FirstEntity;
import com.starter.spring.repository.FirstEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
        FirstEntityDTO firstEntityDTO = mapper.toDto(firstEntity);
        return firstEntityDTO;
    }

    @Override
    public InfoDTO<List<FirstEntityDTO>> list() {
        InfoDTO<List<FirstEntityDTO>> infoDTO = new InfoDTO<>();
        List<FirstEntity> firstEntityList = repository.findAll();
        infoDTO.setMessage("Listagem concluida com sucesso");
        infoDTO.setStatus(HttpStatus.OK);
        infoDTO.setObject(mapper.toListDto(firstEntityList));
        infoDTO.setSuccess(true);
        return infoDTO;
    }

}
