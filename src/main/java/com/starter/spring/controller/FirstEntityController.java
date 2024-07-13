package com.starter.spring.controller;

import com.starter.spring.dto.FirstEntityDTO;
import com.starter.spring.dto.InfoDTO;
import com.starter.spring.service.firstEntity.FirstEntityService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<InfoDTO<List<FirstEntityDTO>>> listAll() {
        try {
            InfoDTO<List<FirstEntityDTO>> list = firstEntityService.list();
            return ResponseEntity.ok(list);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/")
    public ResponseEntity<FirstEntityDTO> create(@Valid @RequestBody FirstEntityDTO obj) {
        FirstEntityDTO firstEntityDTO = firstEntityService.save(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(firstEntityDTO.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

}
