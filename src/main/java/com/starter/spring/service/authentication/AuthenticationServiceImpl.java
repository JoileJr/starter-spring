package com.starter.spring.service.authentication;

import com.starter.spring.config.TokenService;
import com.starter.spring.dto.useCases.LoginRequest;
import com.starter.spring.dto.useCases.LoginResponse;
import com.starter.spring.model.Pessoa;
import com.starter.spring.repository.PessoaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final PessoaRepository pessoaRepository;

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        if (loginRequest.cpf() == null || loginRequest.senha() == null) {
            throw new BadCredentialsException("CPF ou senha não podem ser nulos");
        }

        try {
            var usernamePassword = new UsernamePasswordAuthenticationToken(loginRequest.cpf(), loginRequest.senha());
            var auth = this.authenticationManager.authenticate(usernamePassword);
            var token = tokenService.generateToken((Pessoa) auth.getPrincipal());
            return new LoginResponse(token);
        } catch (Exception e) {
            throw new BadCredentialsException("Falha na autenticação: " + e.getMessage(), e);
        }
    }



}
