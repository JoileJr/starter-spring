package com.starter.spring.service.autenticacao;

import com.starter.spring.service.config.TokenService;
import com.starter.spring.dto.models.PessoaDTO;
import com.starter.spring.dto.useCases.LoginRequest;
import com.starter.spring.dto.useCases.LoginResponse;
import com.starter.spring.model.Pessoa;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AutenticacaoServiceImpl implements AutenticacaoService {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        if (loginRequest.cpf() == null || loginRequest.senha() == null) {
            throw new BadCredentialsException("CPF ou senha não podem ser nulos");
        }

        try {
            var usernamePassword = new UsernamePasswordAuthenticationToken(loginRequest.cpf(), loginRequest.senha());
            Authentication auth = this.authenticationManager.authenticate(usernamePassword);
            Pessoa user = (Pessoa) auth.getPrincipal();
            var token = tokenService.generateToken(user);
            return new LoginResponse(token, auth.getAuthorities(), PessoaDTO.toDTO(user));
        } catch (Exception e) {
            throw new BadCredentialsException("Falha na autenticação: " + e.getMessage(), e);
        }
    }

}
