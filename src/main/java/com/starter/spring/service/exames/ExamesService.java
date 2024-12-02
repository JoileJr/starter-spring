package com.starter.spring.service.exames;

import com.starter.spring.dto.models.ExameDTO;

import java.util.List;

public interface ExamesService {

    ExameDTO criarExame(ExameDTO exameDTO);

    List<ExameDTO> listarExames();

}
