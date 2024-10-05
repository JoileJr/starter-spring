package com.starter.spring.service.autenticacao;

import com.starter.spring.dto.useCases.LoginRequest;
import com.starter.spring.dto.useCases.LoginResponse;

public interface AutenticacaoService {

    LoginResponse login(LoginRequest loginRequest);
}