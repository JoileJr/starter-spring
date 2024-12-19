package com.starter.spring.controller;

import com.starter.spring.dto.models.ExameDTO;
import com.starter.spring.dto.models.ResultadoParametroDTO;
import com.starter.spring.dto.useCases.FindExamByFilterRequest;
import com.starter.spring.service.email.EmailService;
import com.starter.spring.service.exames.ExamesService;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/exam")
@RequiredArgsConstructor
public class ExameController {

    private final ExamesService examesService;
    private final EmailService emailService;

    @PostMapping("/list")
    public ResponseEntity<List<ExameDTO>> listarExames(@RequestBody FindExamByFilterRequest filter) {
        List<ExameDTO> list = examesService.listarExames(filter);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/res/{id}")
    public ResponseEntity<ExameDTO> listarExamesPorID(@PathVariable Long id) {
        ExameDTO dto = examesService.listarExamesPorID(id);
        return ResponseEntity.ok(dto);
    }   

    @GetMapping("/{id}")
    public ResponseEntity<List<ResultadoParametroDTO>> listarResultados(@PathVariable Long id) {
        List<ResultadoParametroDTO> list = examesService.listarResultados(id);
        return ResponseEntity.ok(list);
    }

    @PostMapping("/")
    public ResponseEntity<ExameDTO> create(@Valid @RequestBody ExameDTO obj) {
        ExameDTO firstEntityDTO = examesService.criarExame(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(firstEntityDTO.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/relatorio/pdf/{code}")
    public void exportRelatorio(@PathVariable("code") Long code,
            HttpServletResponse response) throws IOException {
        byte[] bytes = examesService.exportarPDF(code);
        response.setContentType(MediaType.APPLICATION_PDF_VALUE);
        response.getOutputStream().write(bytes);
    }

    @GetMapping("/enviar-resultado/{id}")
    public String enviarResultadoExame(@PathVariable Long id) {
        try {
            emailService.enviarEmailComAnexo(id);
            return "E-mail enviado com sucesso!";
        } catch (MessagingException e) {
            return "Erro ao enviar o e-mail: " + e.getMessage();
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ExameDTO> excluirExame(@PathVariable Long id) {
        examesService.excluirExame(id);
        return ResponseEntity.noContent().build();
    }

}
