package com.starter.spring.service.tipoExame;

import com.starter.spring.dto.models.ParametroDTO;
import com.starter.spring.dto.models.TipoExameDTO;

import java.util.List;

public interface TipoExameService {
    List<TipoExameDTO> listarTipoExames();

    List<ParametroDTO> listarParametros(Long tipoExameId);

}
