package com.starter.spring.service.tipoExame;

import com.starter.spring.dto.models.ParametroDTO;
import com.starter.spring.dto.models.TipoExameDTO;
import com.starter.spring.model.Parametro;
import com.starter.spring.model.TipoExame;
import com.starter.spring.repository.ParametroRepository;
import com.starter.spring.repository.TipoExameRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TipoExameServiceImpl implements TipoExameService {

    private final TipoExameRepository tipoExameRepository;

    private final ParametroRepository parametroRepository;

    @Transactional
    @Override
    public List<TipoExameDTO> listarTipoExames() {
        List<TipoExame> exames = tipoExameRepository.findAll();
        return exames.stream()
                .map(TipoExameDTO::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public List<ParametroDTO> listarParametros(Long tipoExameId) {
        List<Parametro> parametros = parametroRepository.findByTipoExame_Id(tipoExameId);
        return parametros.stream()
                .map(ParametroDTO::toDTO)
                .collect(Collectors.toList());
    }
}
