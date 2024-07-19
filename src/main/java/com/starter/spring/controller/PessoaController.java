package com.starter.spring.controller;

import com.starter.spring.dto.PessoaDTO;
import com.starter.spring.service.Pessoa.PessoaService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
public class PessoaController {

    private final PessoaService pessoaService;

    @GetMapping("/")
    public ResponseEntity<List<PessoaDTO>> listAll() {
        List<PessoaDTO> list = pessoaService.list();
        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "/{id}")
	public ResponseEntity<PessoaDTO> findById(@PathVariable Long id) {
		PessoaDTO obj = pessoaService.findById(id);
		return ResponseEntity.ok().body(obj);
	}

    @PostMapping("/")
    public ResponseEntity<PessoaDTO> create(@Valid @RequestBody PessoaDTO obj) {
        PessoaDTO firstEntityDTO = pessoaService.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(firstEntityDTO.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

	@PutMapping(value = "/{id}")
	public ResponseEntity<PessoaDTO> update(@PathVariable Long id, @Valid @RequestBody PessoaDTO objDTO) {
		PessoaDTO obj = pessoaService.update(id, objDTO);
		return ResponseEntity.ok().body(obj);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<PessoaDTO> delete(@PathVariable Long id) {
		pessoaService.delete(id); 
		return ResponseEntity.noContent().build();
	}

}
