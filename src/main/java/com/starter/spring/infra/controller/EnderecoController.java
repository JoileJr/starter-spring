package com.starter.spring.infra.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.starter.spring.domain.Endereco;
import com.starter.spring.infra.service.EnderecoService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/address")
public class EnderecoController {

    private final EnderecoService enderecoService;

	public EnderecoController(EnderecoService enderecoService) {
		this.enderecoService = enderecoService;
	}

    @GetMapping("/")
    public ResponseEntity<List<Endereco>> listAll() {
        List<Endereco> list = enderecoService.list();
        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "/{id}")
	public ResponseEntity<Endereco> findById(@PathVariable Long id) {
		Endereco obj = enderecoService.findById(id);
		return ResponseEntity.ok().body(obj);
	}

    @PostMapping("/")
    public ResponseEntity<Endereco> create(@Valid @RequestBody Endereco obj) {
        Endereco firstEntityDTO = enderecoService.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(firstEntityDTO.id()).toUri();
        return ResponseEntity.created(uri).build();
    }

	@PutMapping(value = "/{id}")
	public ResponseEntity<Endereco> update(@PathVariable Long id, @Valid @RequestBody Endereco objDTO) {
		Endereco obj = enderecoService.update(id, objDTO);
		return ResponseEntity.ok().body(obj);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Endereco> delete(@PathVariable Long id) {
		enderecoService.delete(id); 
		return ResponseEntity.noContent().build();
	}
}
