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

import com.starter.spring.dto.AdministrativoDTO;
import com.starter.spring.service.admin.AdminService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminstrativoController {

    private final AdminService adminService;

    @GetMapping("/")
    public ResponseEntity<List<AdministrativoDTO>> listAll() {
        List<AdministrativoDTO> list = adminService.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<AdministrativoDTO> findById(@PathVariable Long id) {
        AdministrativoDTO obj = adminService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping("/")
    public ResponseEntity<AdministrativoDTO> create(@Valid @RequestBody AdministrativoDTO obj) {
        AdministrativoDTO firstEntityDTO = adminService.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(firstEntityDTO.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<AdministrativoDTO> update(@PathVariable Long id, @Valid @RequestBody AdministrativoDTO objDTO) {
        AdministrativoDTO obj = adminService.update(id, objDTO);
        return ResponseEntity.ok().body(obj);
    }
    
}