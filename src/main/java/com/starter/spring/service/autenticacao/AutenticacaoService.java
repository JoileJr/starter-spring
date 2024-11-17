package com.starter.spring.service.autenticacao;

import com.starter.spring.dto.useCases.LoginRequest;
import com.starter.spring.dto.useCases.LoginResponse;
import com.starter.spring.dto.useCases.SingUpRequest;

public interface AutenticacaoService {

    LoginResponse login(LoginRequest loginRequest);

    SingUpRequest singUp (SingUpRequest request);
}