package com.starter.spring.service.autenticacao;

import com.starter.spring.service.config.TokenService;

import jakarta.transaction.Transactional;

import com.starter.spring.dto.models.PessoaDTO;
import com.starter.spring.dto.useCases.LoginRequest;
import com.starter.spring.dto.useCases.LoginResponse;
import com.starter.spring.dto.useCases.SingUpRequest;
import com.starter.spring.enums.TipoUsuario;
import com.starter.spring.exceptions.DataIntegrityViolationException;
import com.starter.spring.model.Perfil;
import com.starter.spring.model.Pessoa;
import com.starter.spring.repository.PerfilRepository;
import com.starter.spring.repository.PessoaRepository;

import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AutenticacaoServiceImpl implements AutenticacaoService {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final PessoaRepository pessoaRepository;
    private final PerfilRepository perfilRepository;

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

    @Transactional
    @Override
    public SingUpRequest singUp(SingUpRequest objDTO) {
        validateByEmailAndCpf(objDTO);
        Set<Perfil> perfis = getDefaultProfiles(objDTO.getPerfis());
        objDTO.setSenha(new BCryptPasswordEncoder().encode(objDTO.getSenha()));
        Pessoa paciente = SingUpRequest.toEntity(objDTO);
        paciente.setPerfis(perfis);
        paciente.setAtivo(true);
        paciente = pessoaRepository.save(paciente);
        return SingUpRequest.toDTO(paciente);
    }

    private void validateByEmailAndCpf(SingUpRequest objDTO) {
        Optional<Pessoa> obj = pessoaRepository.findByCpfOrEmail(objDTO.getCpf(), objDTO.getEmail());
        if (obj.isPresent()) {
            throw new DataIntegrityViolationException("CPF ou E-mail já cadastrado no sistema!");
        }
    }

    private Set<Perfil> getDefaultProfiles(String perfisBuscar) {
        Set<Perfil> perfis = new HashSet<>();
        Perfil perfil;
        if (perfisBuscar.equals(TipoUsuario.ADMINSTRADOR.getDescricao())) {
            perfil = perfilRepository.findByNome(TipoUsuario.ADMINSTRADOR.getDescricao());
        } else {
            perfil = perfilRepository.findByNome(TipoUsuario.PACIENTE.getDescricao());
        }
        if (perfil != null) {
            perfis.add(perfil);
        }
        return perfis;
    }

}
