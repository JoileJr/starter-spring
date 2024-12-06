package com.starter.spring.service.exames;

import com.starter.spring.dto.models.ExameDTO;
import com.starter.spring.dto.models.ResultadoParametroDTO;
import com.starter.spring.dto.useCases.FindExamByFilterRequest;
import com.starter.spring.model.Exame;

import java.util.List;

public interface ExamesService {

    ExameDTO criarExame(ExameDTO exameDTO);

    List<ExameDTO> listarExames(FindExamByFilterRequest filter);

    List<ResultadoParametroDTO> listarResultados(Long IdExame);

}
