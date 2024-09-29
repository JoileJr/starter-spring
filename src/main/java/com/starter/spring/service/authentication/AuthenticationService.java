package com.starter.spring.service.authentication;

import com.starter.spring.dto.useCases.LoginRequest;
import com.starter.spring.dto.useCases.LoginResponse;

public interface AuthenticationService {

    LoginResponse login(LoginRequest loginRequest);
}