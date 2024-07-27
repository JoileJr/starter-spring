package com.starter.spring.infra.controller;

import com.starter.spring.domain.Pessoa;
import com.starter.spring.infra.service.PessoaService;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/person")
public class PessoaController {

    private final PessoaService pessoaService;

	public PessoaController(PessoaService pessoaService) {
		this.pessoaService = pessoaService;
	}

    @GetMapping("/")
    public ResponseEntity<List<Pessoa>> listAll() {
        List<Pessoa> list = pessoaService.list();
        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "/{id}")
	public ResponseEntity<Pessoa> findById(@PathVariable Long id) {
		Pessoa obj = pessoaService.findById(id);
		return ResponseEntity.ok().body(obj);
	}

    @PostMapping("/")
    public ResponseEntity<Pessoa> create(@Valid @RequestBody Pessoa obj) {
        Pessoa pessoa = pessoaService.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pessoa.id()).toUri();
        return ResponseEntity.created(uri).build();
    }

	@PutMapping(value = "/{id}")
	public ResponseEntity<Pessoa> update(@PathVariable Long id, @Valid @RequestBody Pessoa objDTO) {
		Pessoa obj = pessoaService.update(id, objDTO);
		return ResponseEntity.ok().body(obj);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Pessoa> delete(@PathVariable Long id) {
		pessoaService.delete(id); 
		return ResponseEntity.noContent().build();
	}

}
