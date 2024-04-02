package com.starter.spring.controller;

import com.starter.spring.dto.FirstEntityDTO;
import com.starter.spring.dto.InfoDTO;
import com.starter.spring.service.firstEntity.FirstEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<InfoDTO<FirstEntityDTO>> create(@RequestBody FirstEntityDTO obj) {
        try {
            InfoDTO<FirstEntityDTO> firstEntityDTO = firstEntityService.save(obj);
            return ResponseEntity.ok(firstEntityDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
