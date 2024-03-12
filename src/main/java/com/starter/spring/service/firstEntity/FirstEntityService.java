package com.starter.spring.service.firstEntity;

import com.starter.spring.dto.FirstEntityDTO;

import java.util.List;

public interface FirstEntityService {

    FirstEntityDTO save(FirstEntityDTO dto);

    List<FirstEntityDTO> list();

}
