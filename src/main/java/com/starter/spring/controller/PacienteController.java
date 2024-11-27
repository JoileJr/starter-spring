package com.starter.spring.controller;

import java.net.URI;
import java.util.List;

import com.starter.spring.dto.models.PessoaDTO;
import com.starter.spring.dto.useCases.FilterPersonsRequest;

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

import com.starter.spring.service.paciente.PacienteService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/pac")
@RequiredArgsConstructor
public class PacienteController {

    private final PacienteService pacienteService;

    @GetMapping("/")
    public ResponseEntity<List<PessoaDTO>> listAll() {
        List<PessoaDTO> list = pacienteService.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PessoaDTO> findById(@PathVariable Long id) {
        PessoaDTO obj = pacienteService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping(value = "/find/{cpf}")
    public ResponseEntity<PessoaDTO> findById(@PathVariable String cpf) {
        PessoaDTO obj = pacienteService.findByCpf(cpf);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping("/find")
    public ResponseEntity<List<PessoaDTO>> findByFilter(@Valid @RequestBody FilterPersonsRequest obj) {
        List<PessoaDTO> list = pacienteService.findByFilter(obj);
        return ResponseEntity.ok(list);
    }

    @PostMapping("/")
    public ResponseEntity<PessoaDTO> create(@Valid @RequestBody PessoaDTO obj) {
        PessoaDTO firstEntityDTO = pacienteService.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(firstEntityDTO.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<PessoaDTO> update(@PathVariable Long id, @Valid @RequestBody PessoaDTO objDTO) {
        PessoaDTO obj = pacienteService.update(id, objDTO);
        return ResponseEntity.ok().body(obj);
    }

}
