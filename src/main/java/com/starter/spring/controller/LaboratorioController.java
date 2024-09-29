package com.starter.spring.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.starter.spring.dto.models.LaboratorioDTO;
import com.starter.spring.service.laboratorio.LaboratorioService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/lab")
@RequiredArgsConstructor
public class LaboratorioController {

    private final LaboratorioService laboratorioService;

    @GetMapping("/")
    public ResponseEntity<List<LaboratorioDTO>> listAll() {
        List<LaboratorioDTO> list = laboratorioService.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<LaboratorioDTO> findById(@PathVariable Long id) {
        LaboratorioDTO obj = laboratorioService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping("/")
    public ResponseEntity<LaboratorioDTO> create(@Valid @RequestBody LaboratorioDTO obj) {
        LaboratorioDTO firstEntityDTO = laboratorioService.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(firstEntityDTO.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<LaboratorioDTO> update(@PathVariable Long id,
            @Valid @RequestBody LaboratorioDTO objDTO) {
        LaboratorioDTO obj = laboratorioService.update(id, objDTO);
        return ResponseEntity.ok().body(obj);
    }
    
}
