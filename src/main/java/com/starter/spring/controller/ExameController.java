package com.starter.spring.controller;

import com.starter.spring.dto.models.ExameDTO;
import com.starter.spring.service.exames.ExamesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/exam")
@RequiredArgsConstructor
public class ExameController {

    private final ExamesService examesService;

    @GetMapping("/")
    public ResponseEntity<List<ExameDTO>> listarExames() {
        List<ExameDTO> list = examesService.listarExames();
        return ResponseEntity.ok(list);
    }

    @PostMapping("/")
    public ResponseEntity<ExameDTO> create(@Valid @RequestBody ExameDTO obj) {
        ExameDTO firstEntityDTO = examesService.criarExame(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(firstEntityDTO.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

}
