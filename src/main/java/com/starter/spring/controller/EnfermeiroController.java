package com.starter.spring.controller;

import com.starter.spring.dto.EnfermeiroDTO;
import com.starter.spring.service.enfermeiro.EnfermeiroService;

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
public class EnfermeiroController {

    private final EnfermeiroService enfermeiroService;

    @GetMapping("/")
    public ResponseEntity<List<EnfermeiroDTO>> listAll() {
        List<EnfermeiroDTO> list = enfermeiroService.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "/{id}")
	public ResponseEntity<EnfermeiroDTO> findById(@PathVariable Long id) {
        EnfermeiroDTO obj = enfermeiroService.findById(id);
		return ResponseEntity.ok().body(obj);
	}

    @PostMapping("/")
    public ResponseEntity<EnfermeiroDTO> create(@Valid @RequestBody EnfermeiroDTO obj) {
        EnfermeiroDTO firstEntityDTO = enfermeiroService.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(firstEntityDTO.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

	@PutMapping(value = "/{id}")
	public ResponseEntity<EnfermeiroDTO> update(@PathVariable Long id, @Valid @RequestBody EnfermeiroDTO objDTO) {
		EnfermeiroDTO obj = enfermeiroService.update(id, objDTO);
		return ResponseEntity.ok().body(obj);
	}
    
}
