package com.starter.spring.service.exames;

import com.starter.spring.dto.models.ExameDTO;
import com.starter.spring.dto.models.ResultadoParametroDTO;
import com.starter.spring.dto.useCases.FindExamByFilterRequest;

import java.util.List;

public interface ExamesService {

    void addParams(String key, Object value);

    byte[] exportarPDF(Long code);

    ExameDTO criarExame(ExameDTO exameDTO);

    List<ExameDTO> listarExames(FindExamByFilterRequest filter);

    List<ResultadoParametroDTO> listarResultados(Long IdExame);

}
