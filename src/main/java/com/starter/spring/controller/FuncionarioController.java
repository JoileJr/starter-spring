package com.starter.spring.controller;

import com.starter.spring.dto.models.ProfissionalSaudeDTO;
import com.starter.spring.dto.useCases.FilterHealthProfessionalRequest;
import com.starter.spring.dto.useCases.ProfissionalSaudeRequest;
import com.starter.spring.service.funcionarios.FuncionarioService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/enf")
@RequiredArgsConstructor
public class FuncionarioController {

    private final FuncionarioService enfermeiroService;

    @GetMapping("/")
    public ResponseEntity<List<ProfissionalSaudeDTO>> listAll() {
        List<ProfissionalSaudeDTO> list = enfermeiroService.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProfissionalSaudeDTO> findById(@PathVariable Long id) {
        ProfissionalSaudeDTO obj = enfermeiroService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping("/")
    public ResponseEntity<ProfissionalSaudeDTO> create(@Valid @RequestBody ProfissionalSaudeRequest obj) {
        ProfissionalSaudeDTO firstEntityDTO = enfermeiroService.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(firstEntityDTO.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PostMapping("/find")
    public ResponseEntity<List<ProfissionalSaudeDTO>> findByFilter(@Valid @RequestBody FilterHealthProfessionalRequest obj) {
        List<ProfissionalSaudeDTO> list = enfermeiroService.findByFilter(obj);
        return ResponseEntity.ok(list);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ProfissionalSaudeDTO> update(@PathVariable Long id, @Valid @RequestBody ProfissionalSaudeRequest objDTO) {
        ProfissionalSaudeDTO obj = enfermeiroService.update(id, objDTO);
        return ResponseEntity.ok().body(obj);
    }

}
