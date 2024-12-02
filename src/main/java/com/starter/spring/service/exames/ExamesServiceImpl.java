package com.starter.spring.service.exames;

import com.starter.spring.dto.models.ExameDTO;
import com.starter.spring.dto.models.ProfissionalSaudeDTO;
import com.starter.spring.dto.models.ResultadoParametroDTO;
import com.starter.spring.dto.models.TipoExameDTO;
import com.starter.spring.model.Exame;
import com.starter.spring.model.ProfissionalSaude;
import com.starter.spring.model.ResultadoParametro;
import com.starter.spring.model.TipoExame;
import com.starter.spring.repository.ExameRepository;
import com.starter.spring.repository.ResultadoParametroRepository;
import com.starter.spring.repository.TipoExameRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExamesServiceImpl implements ExamesService {
    private final ExameRepository exameRepository;
    private final ResultadoParametroRepository resultadoParametroRepository;

    @Transactional
    @Override
    public ExameDTO criarExame(ExameDTO exameDTO) {
        Exame exame = ExameDTO.toEntity(exameDTO);
        exame = exameRepository.save(exame);

        for (ResultadoParametroDTO resultadoParametro : exameDTO.getResultadoParametros()) {
            resultadoParametro.setExame(ExameDTO.toDTO(exame));
            ResultadoParametro rp = this.resultadoParametroRepository.save(ResultadoParametroDTO.toEntity(resultadoParametro));
        }

        return ExameDTO.toDTO(exame);
    }

    @Transactional
    @Override
    public List<ExameDTO> listarExames() {
        List<Exame> exames = exameRepository.findAll();
        List<ExameDTO> examesDTO = new ArrayList<>();

        for (Exame exame : exames) {
            List<ResultadoParametro> rps = resultadoParametroRepository.findByExame_IdAndParametro_TipoExame_Id(exame.getId(), exame.getTipoExame().getId());
            List<ResultadoParametroDTO> rpsDTO = rps.stream().map(ResultadoParametroDTO::toDTO).toList();
            ExameDTO dto = ExameDTO.toDTO(exame);
            dto.setResultadoParametros(rpsDTO);
            examesDTO.add(dto);
        }
        return examesDTO;
    }

    @Transactional
    @Override
    public List<ResultadoParametroDTO> listarResultados(Long IdExame) {
        List<ResultadoParametro> rps = resultadoParametroRepository.findByExame_Id(IdExame);
        List<ResultadoParametroDTO> rpsDTO = rps.stream().map(ResultadoParametroDTO::toDTO).toList();
        return rpsDTO;
    }

}
