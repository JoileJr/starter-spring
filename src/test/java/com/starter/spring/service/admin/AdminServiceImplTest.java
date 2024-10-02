package com.starter.spring.service.admin;

import com.starter.spring.dto.models.AdministrativoDTO;
import com.starter.spring.dto.models.LaboratorioDTO;
import com.starter.spring.exceptions.DataIntegrityViolationException;
import com.starter.spring.exceptions.ObjectnotFoundException;
import com.starter.spring.model.Administrativo;
import com.starter.spring.model.Pessoa;
import com.starter.spring.repository.AdministrativoRepository;
import com.starter.spring.repository.LaboratorioRepository;
import com.starter.spring.repository.PerfilRepository;
import com.starter.spring.repository.PessoaRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;


class AdminServiceImplTest {

    @InjectMocks
    private AdminServiceImpl administrativoService;

    @Mock
    private AdministrativoRepository administrativoRepository;

    @Mock
    private PessoaRepository pessoaRepository;

    @Mock
    private LaboratorioRepository laboratorioRepository;

    @Mock
    private PerfilRepository perfilRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindById() {
        Long id = 1L;
        AdministrativoDTO administrativoDTO = criaAdministrativo();
        Administrativo administrativo = AdministrativoDTO.toEntity(administrativoDTO);
        when(administrativoRepository.findById(id)).thenReturn(Optional.of(administrativo));

        AdministrativoDTO result = administrativoService.findById(id);

        assertNotNull(result);
        verify(administrativoRepository).findById(id);
    }

    @Test
    void testFindById_NotFound() {
        Long id = 1L;
        when(administrativoRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ObjectnotFoundException.class, () -> {
            administrativoService.findById(id);
        });

        verify(administrativoRepository).findById(id);
    }

    @Test
    void testCreate() {
        AdministrativoDTO administrativoDTO = criaAdministrativo();
        Administrativo administrativo = AdministrativoDTO.toEntity(administrativoDTO);

        when(pessoaRepository.findByCpfOrEmail(anyString(), anyString())).thenReturn(Optional.empty());
        when(administrativoRepository.save(any(Administrativo.class))).thenReturn(administrativo);

        AdministrativoDTO result = administrativoService.create(administrativoDTO);

        assertNotNull(result);
        verify(administrativoRepository).save(any(Administrativo.class));
    }

    @Test
    void testCreate_WithExistingCpfOrEmail() {
        AdministrativoDTO administrativoDTO = criaAdministrativo();
        when(pessoaRepository.findByCpfOrEmail(anyString(), anyString())).thenReturn(Optional.of(new Pessoa()));

        assertThrows(DataIntegrityViolationException.class, () -> {
            administrativoService.create(administrativoDTO);
        });

        verify(administrativoRepository, never()).save(any(Administrativo.class));
    }

    @Test
    void testUpdate() {
        Long id = 1L;
        AdministrativoDTO administrativoDTO = criaAdministrativo();
        administrativoDTO.setId(id);
        Administrativo administrativo = AdministrativoDTO.toEntity(administrativoDTO);

        when(administrativoRepository.save(any(Administrativo.class))).thenReturn(administrativo);

        AdministrativoDTO result = administrativoService.update(id, administrativoDTO);

        assertNotNull(result);
        verify(administrativoRepository).save(any(Administrativo.class));
    }

    @Test
    void testUpdate_NotFound() {
        Long id = 1L;
        AdministrativoDTO administrativoDTO = criaAdministrativo();
        administrativoDTO.setId(id);

        when(administrativoRepository.save(any(Administrativo.class))).thenThrow(new ObjectnotFoundException("Administrativo não encontrado"));

        assertThrows(ObjectnotFoundException.class, () -> {
            administrativoService.update(id, administrativoDTO);
        });

        verify(administrativoRepository).save(any(Administrativo.class));
    }

    private AdministrativoDTO criaAdministrativo() {
        AdministrativoDTO administrativoDTO = new AdministrativoDTO();
        administrativoDTO.setId(1L);
        administrativoDTO.setNome("Maria da Silva");
        administrativoDTO.setCpf("123.456.789-00");
        administrativoDTO.setTelefone("98765-4321");
        administrativoDTO.setSexo("Feminino");
        administrativoDTO.setEmail("maria@example.com");
        administrativoDTO.setSenha("senha123");
        administrativoDTO.setDataNascimento(new Date());
        administrativoDTO.setLaboratorio(new LaboratorioDTO());
        administrativoDTO.setPerfis(new HashSet<>());
        return administrativoDTO;
    }
}