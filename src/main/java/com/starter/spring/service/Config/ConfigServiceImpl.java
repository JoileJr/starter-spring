package com.starter.spring.service.Config;

import java.util.Arrays;

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

        Perfil p1 = Perfil.builder().nome("paciente").build();
        Perfil p2 = Perfil.builder().nome("enfermeiro").build();
        Perfil p3 = Perfil.builder().nome("administrativo").build();

        repository.saveAll(Arrays.asList(p1, p2, p3));
    }

    @Override
    public Boolean perfisInstanciados() {
        return repository.count() > 0;
    }
    
}
