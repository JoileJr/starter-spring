package com.starter.spring.controller;

import com.starter.spring.dto.models.ParametroDTO;
import com.starter.spring.dto.models.TipoExameDTO;
import com.starter.spring.service.tipoExame.TipoExameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/tipoExame")
@RequiredArgsConstructor
public class TipoExameController {
    private final TipoExameService tipoExameService;

    @GetMapping("/")
    public ResponseEntity<List<TipoExameDTO>> listarTipoExames() {
        List<TipoExameDTO> list = tipoExameService.listarTipoExames();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/parametros/{id}")
    public ResponseEntity<List<ParametroDTO>> listarParametros(@PathVariable Long id) {
        List<ParametroDTO> list = tipoExameService.listarParametros(id);
        return ResponseEntity.ok(list);
    }
}
