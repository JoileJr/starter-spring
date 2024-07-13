package com.starter.spring.controller;

import com.starter.spring.dto.FirstEntityDTO;
import com.starter.spring.service.FirstEntityService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/example")
@RequiredArgsConstructor
public class FirstEntityController {

    private final FirstEntityService firstEntityService;

    @GetMapping("/")
    public ResponseEntity<List<FirstEntityDTO>> listAll() {
        List<FirstEntityDTO> list = firstEntityService.list();
        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "/{id}")
	public ResponseEntity<FirstEntityDTO> findById(@PathVariable Long id) {
		FirstEntityDTO obj = firstEntityService.findById(id);
		return ResponseEntity.ok().body(obj);
	}

    @PostMapping("/")
    public ResponseEntity<FirstEntityDTO> create(@Valid @RequestBody FirstEntityDTO obj) {
        FirstEntityDTO firstEntityDTO = firstEntityService.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(firstEntityDTO.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

	@PutMapping(value = "/{id}")
	public ResponseEntity<FirstEntityDTO> update(@PathVariable Long id, @Valid @RequestBody FirstEntityDTO objDTO) {
		FirstEntityDTO obj = firstEntityService.update(id, objDTO);
		return ResponseEntity.ok().body(obj);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<FirstEntityDTO> delete(@PathVariable Long id) {
		firstEntityService.delete(id); 
		return ResponseEntity.noContent().build();
	}

}
