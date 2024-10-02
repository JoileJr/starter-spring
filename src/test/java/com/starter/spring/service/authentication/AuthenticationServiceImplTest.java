package com.starter.spring.service.authentication;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.starter.spring.config.TokenService;
import com.starter.spring.dto.useCases.LoginRequest;
import com.starter.spring.dto.useCases.LoginResponse;
import com.starter.spring.model.Pessoa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;

public class AuthenticationServiceImplTest {

    @InjectMocks
    private AuthenticationServiceImpl authenticationService;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private TokenService tokenService;

    @Mock
    private UserDetailsService userDetailsService;

    @Mock
    private Authentication authentication;

    private Pessoa pessoa;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        pessoa = new Pessoa();
        pessoa.setId(1L);
        pessoa.setCpf("123.456.789-00");
        pessoa.setNome("João");
    }

    @Test
    void testLogin_Success() {
        String cpf = "123.456.789-00";
        String senha = "senha123";
        LoginRequest loginRequest = new LoginRequest(cpf, senha);
        String token = "token_example";

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn(pessoa);
        when(tokenService.generateToken(any(Pessoa.class))).thenReturn(token);

        LoginResponse response = authenticationService.login(loginRequest);

        assertNotNull(response);
        assertEquals(token, response.token());
        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
    }

    @Test
    void testLogin_NullCpf() {
        LoginRequest loginRequest = new LoginRequest(null, "senha123");

        BadCredentialsException exception = assertThrows(BadCredentialsException.class, () -> {
            authenticationService.login(loginRequest);
        });

        assertEquals("CPF ou senha não podem ser nulos", exception.getMessage());
    }

    @Test
    void testLogin_NullSenha() {
        LoginRequest loginRequest = new LoginRequest("123.456.789-00", null);

        BadCredentialsException exception = assertThrows(BadCredentialsException.class, () -> {
            authenticationService.login(loginRequest);
        });

        assertEquals("CPF ou senha não podem ser nulos", exception.getMessage());
    }

    @Test
    void testLogin_FailedAuthentication() {
        String cpf = "123.456.789-00";
        String senha = "senhaErrada";
        LoginRequest loginRequest = new LoginRequest(cpf, senha);

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenThrow(new BadCredentialsException("Credenciais inválidas"));

        BadCredentialsException exception = assertThrows(BadCredentialsException.class, () -> {
            authenticationService.login(loginRequest);
        });

        assertEquals("Falha na autenticação: Credenciais inválidas", exception.getMessage());
    }
}
