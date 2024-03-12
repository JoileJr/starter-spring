package com.starter.spring.controller;

import com.starter.spring.dto.FirstEntityDTO;
import com.starter.spring.service.firstEntity.FirstEntityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/example")
@RequiredArgsConstructor
@Slf4j
public class FirstEntityController {

    @Autowired
    private FirstEntityService firstEntityService;

    @GetMapping("/")
    public ResponseEntity<List<FirstEntityDTO>> listAll() {
        try {
            List<FirstEntityDTO> list = firstEntityService.list();
            return ResponseEntity.ok(list);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/")
    public ResponseEntity<FirstEntityDTO> create(@RequestBody FirstEntityDTO obj) {
        try {
            FirstEntityDTO firstEntityDTO = firstEntityService.save(obj);
            return ResponseEntity.ok(firstEntityDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
