package com.starter.spring.controller;

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

import com.starter.spring.dto.EnderecoDTO;
import com.starter.spring.service.Endereco.EnderecoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/address")
@RequiredArgsConstructor
public class EnderecoController {

    private final EnderecoService enderecoService;

    @GetMapping("/")
    public ResponseEntity<List<EnderecoDTO>> listAll() {
        List<EnderecoDTO> list = enderecoService.list();
        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "/{id}")
	public ResponseEntity<EnderecoDTO> findById(@PathVariable Long id) {
		EnderecoDTO obj = enderecoService.findById(id);
		return ResponseEntity.ok().body(obj);
	}

    @PostMapping("/")
    public ResponseEntity<EnderecoDTO> create(@Valid @RequestBody EnderecoDTO obj) {
        EnderecoDTO firstEntityDTO = enderecoService.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(firstEntityDTO.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

	@PutMapping(value = "/{id}")
	public ResponseEntity<EnderecoDTO> update(@PathVariable Long id, @Valid @RequestBody EnderecoDTO objDTO) {
		EnderecoDTO obj = enderecoService.update(id, objDTO);
		return ResponseEntity.ok().body(obj);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<EnderecoDTO> delete(@PathVariable Long id) {
		enderecoService.delete(id); 
		return ResponseEntity.noContent().build();
	}
}
