package com.starter.spring.service.firstEntity;

import com.starter.spring.dto.FirstEntityDTO;
import com.starter.spring.dto.InfoDTO;

import java.util.List;

public interface FirstEntityService {

    InfoDTO<FirstEntityDTO> save(FirstEntityDTO dto);

    InfoDTO<List<FirstEntityDTO>> list();

}
