package com.starter.spring.controller;

import com.starter.spring.dto.useCases.LoginRequest;
import com.starter.spring.dto.useCases.LoginResponse;
import com.starter.spring.dto.useCases.SingUpRequest;
import com.starter.spring.service.autenticacao.AutenticacaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AutenticacaoController {

    private final AutenticacaoService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest loginRequest) {
        LoginResponse LoginResponse = authenticationService.login(loginRequest);
        return ResponseEntity.ok(LoginResponse);
    }

    @PostMapping("/sing-up")
    public ResponseEntity<SingUpRequest> singUp(@RequestBody @Valid SingUpRequest request) {
        SingUpRequest pessoaDTO = authenticationService.singUp(request);
        return ResponseEntity.ok(pessoaDTO);
    }
}
