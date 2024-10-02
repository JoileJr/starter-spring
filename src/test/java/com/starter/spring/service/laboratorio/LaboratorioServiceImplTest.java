package com.starter.spring.service.laboratorio;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.starter.spring.dto.models.EnderecoDTO;
import com.starter.spring.dto.models.LaboratorioDTO;
import com.starter.spring.exceptions.DataIntegrityViolationException;
import com.starter.spring.exceptions.ObjectnotFoundException;
import com.starter.spring.model.Laboratorio;
import com.starter.spring.repository.EnderecoRepository;
import com.starter.spring.repository.LaboratorioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

class LaboratorioServiceImplTest {

    @InjectMocks
    private LaboratorioServiceImpl laboratorioService;

    @Mock
    private LaboratorioRepository laboratorioRepository;

    @Mock
    private EnderecoRepository enderecoRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindById() {
        Long id = 1L;
        LaboratorioDTO laboratorioDTO = criaLaboratorio();
        Laboratorio laboratorio = LaboratorioDTO.toEntity(laboratorioDTO);
        when(laboratorioRepository.findById(id)).thenReturn(Optional.of(laboratorio));

        LaboratorioDTO result = laboratorioService.findById(id);

        assertNotNull(result);
        verify(laboratorioRepository).findById(id);
    }

    @Test
    void testFindById_NotFound() {
        Long id = 1L;
        when(laboratorioRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ObjectnotFoundException.class, () -> {
            laboratorioService.findById(id);
        });

        verify(laboratorioRepository).findById(id);
    }

    @Test
    void testFindAll() {
        LaboratorioDTO laboratorioDTO = criaLaboratorio();
        Laboratorio laboratorio = LaboratorioDTO.toEntity(laboratorioDTO);
        when(laboratorioRepository.findAll()).thenReturn(Collections.singletonList(laboratorio));

        List<LaboratorioDTO> result = laboratorioService.findAll();

        assertFalse(result.isEmpty());
        verify(laboratorioRepository).findAll();
    }

    @Test
    void testCreate() {
        LaboratorioDTO laboratorioDTO = criaLaboratorio();
        Laboratorio laboratorio = LaboratorioDTO.toEntity(laboratorioDTO);

        when(laboratorioRepository.findByCnpj(laboratorioDTO.getCnpj())).thenReturn(Optional.empty());
        when(enderecoRepository.save(any())).thenReturn(EnderecoDTO.toEntity(laboratorioDTO.getEndereco()));
        when(laboratorioRepository.save(any(Laboratorio.class))).thenReturn(laboratorio);

        LaboratorioDTO result = laboratorioService.create(laboratorioDTO);

        assertNotNull(result);
        verify(laboratorioRepository).save(any(Laboratorio.class));
    }

    @Test
    void testCreate_WithExistingCnpj() {
        LaboratorioDTO laboratorioDTO = criaLaboratorio();
        Laboratorio existingLaboratorio = new Laboratorio();
        existingLaboratorio.setCnpj(laboratorioDTO.getCnpj());

        when(laboratorioRepository.findByCnpj(laboratorioDTO.getCnpj())).thenReturn(Optional.of(existingLaboratorio));

        assertThrows(DataIntegrityViolationException.class, () -> {
            laboratorioService.create(laboratorioDTO);
        });

        verify(laboratorioRepository, never()).save(any(Laboratorio.class));
    }

    @Test
    void testUpdate() {
        Long id = 1L;
        LaboratorioDTO laboratorioDTO = criaLaboratorio();
        laboratorioDTO.setId(id);
        Laboratorio laboratorio = LaboratorioDTO.toEntity(laboratorioDTO);

        when(laboratorioRepository.save(any(Laboratorio.class))).thenReturn(laboratorio);
        when(enderecoRepository.save(any())).thenReturn(EnderecoDTO.toEntity(laboratorioDTO.getEndereco()));

        LaboratorioDTO result = laboratorioService.update(id, laboratorioDTO);

        assertNotNull(result);
        verify(laboratorioRepository).save(any(Laboratorio.class));
    }

    private LaboratorioDTO criaLaboratorio() {
        LaboratorioDTO laboratorioDTO = new LaboratorioDTO();
        laboratorioDTO.setId(1L);
        laboratorioDTO.setNome("Laboratório XYZ");
        laboratorioDTO.setCnpj("12.345.678/0001-90");
        laboratorioDTO.setTelefone("12345-6789");
        laboratorioDTO.setRazaoSocial("Laboratório XYZ LTDA");
        laboratorioDTO.setEmail("contato@laboratorioxyz.com");
        laboratorioDTO.setEndereco(new EnderecoDTO());
        laboratorioDTO.setEnfermeiros(Collections.emptyList());
        laboratorioDTO.setAdministrativos(Collections.emptyList());
        laboratorioDTO.setExames(Collections.emptyList());
        return laboratorioDTO;
    }
}