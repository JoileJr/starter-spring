package com.starter.spring.service.config;

import java.util.Arrays;

import com.starter.spring.enums.TipoUsuario;
import org.springframework.stereotype.Service;

import com.starter.spring.model.Perfil;
import com.starter.spring.repository.PerfilRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ConfigServiceImpl implements ConfigService {

    private final PerfilRepository repository;

    @Override
    public void instanciaPerfil() {

        Perfil p1 = Perfil.builder().nome(TipoUsuario.ADMINSTRATIVO.getDescricao()).build();
        Perfil p2 = Perfil.builder().nome(TipoUsuario.ADMINSTRADOR.getDescricao()).build();
        Perfil p3 = Perfil.builder().nome(TipoUsuario.MEDICO.getDescricao()).build();
        Perfil p4 = Perfil.builder().nome(TipoUsuario.BIOMEDICO.getDescricao()).build();
        Perfil p5 = Perfil.builder().nome(TipoUsuario.PACIENTE.getDescricao()).build();
        Perfil p6 = Perfil.builder().nome(TipoUsuario.ENFERMEIRO.getDescricao()).build();
        Perfil p7 = Perfil.builder().nome(TipoUsuario.TECNICO_ENFERMAGEM.getDescricao()).build();

        repository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7));
    }

    @Override
    public Boolean perfisInstanciados() {
        return repository.count() > 0;
    }
    
}
