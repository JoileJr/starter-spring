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

import com.starter.spring.dto.PacienteDTO;
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
    public ResponseEntity<List<PacienteDTO>> listAll() {
        List<PacienteDTO> list = pacienteService.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "/{id}")
	public ResponseEntity<PacienteDTO> findById(@PathVariable Long id) {
        PacienteDTO obj = pacienteService.findById(id);
		return ResponseEntity.ok().body(obj);
	}

    @PostMapping("/")
    public ResponseEntity<PacienteDTO> create(@Valid @RequestBody PacienteDTO obj) {
        PacienteDTO firstEntityDTO = pacienteService.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(firstEntityDTO.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

	@PutMapping(value = "/{id}")
	public ResponseEntity<PacienteDTO> update(@PathVariable Long id, @Valid @RequestBody PacienteDTO objDTO) {
		PacienteDTO obj = pacienteService.update(id, objDTO);
		return ResponseEntity.ok().body(obj);
	}
    
}
