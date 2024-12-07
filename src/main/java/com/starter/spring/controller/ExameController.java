package com.starter.spring.controller;

import com.starter.spring.dto.models.ExameDTO;
import com.starter.spring.dto.models.ResultadoParametroDTO;
import com.starter.spring.dto.useCases.FindExamByFilterRequest;
import com.starter.spring.service.exames.ExamesService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/exam")
@RequiredArgsConstructor
public class ExameController {

    private final ExamesService examesService;

    @PostMapping("/list")
    public ResponseEntity<List<ExameDTO>> listarExames(@RequestBody FindExamByFilterRequest filter) {
        List<ExameDTO> list = examesService.listarExames(filter);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<ResultadoParametroDTO>> listarResultados(@PathVariable Long id) {
        List<ResultadoParametroDTO> list = examesService.listarResultados(id);
        return ResponseEntity.ok(list);
    }

    @PostMapping("/")
    public ResponseEntity<ExameDTO> create(@Valid @RequestBody ExameDTO obj) {
        ExameDTO firstEntityDTO = examesService.criarExame(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(firstEntityDTO.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/relatorio/pdf/{code}")
    public void exportRelatorio(@PathVariable("code") Long code,
                                HttpServletResponse response) throws IOException {
        byte[] bytes = examesService.exportarPDF(code);
        response.setContentType(MediaType.APPLICATION_PDF_VALUE);
        response.getOutputStream().write(bytes);
    }

}
