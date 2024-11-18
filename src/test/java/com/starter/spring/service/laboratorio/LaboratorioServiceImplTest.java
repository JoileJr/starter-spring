package com.starter.spring.service.laboratorio;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.starter.spring.dto.models.EnderecoDTO;
import com.starter.spring.dto.models.LaboratorioDTO;
import com.starter.spring.dto.useCases.LaboratorioCreateRequest;
import com.starter.spring.exceptions.DataIntegrityViolationException;
import com.starter.spring.exceptions.ObjectnotFoundException;
import com.starter.spring.model.Laboratorio;
import com.starter.spring.model.Pessoa;
import com.starter.spring.repository.EnderecoRepository;
import com.starter.spring.repository.LaboratorioRepository;
import com.starter.spring.repository.PessoaRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

class LaboratorioServiceImplTest {

    @InjectMocks
    private LaboratorioServiceImpl laboratorioService;

    @Mock
    private LaboratorioRepository laboratorioRepository;

    @Mock
    private PessoaRepository pessoaRepository;

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
        LaboratorioCreateRequest laboratorioDTO = criaLaboratorioRequest();
        Laboratorio laboratorio = LaboratorioCreateRequest.toEntity(laboratorioDTO);

        when(pessoaRepository.findById(laboratorioDTO.getIdAdm())).thenReturn(Optional.of(this.criaAdmin()));
        when(laboratorioRepository.findByCnpj(laboratorioDTO.getCnpj())).thenReturn(Optional.empty());
        when(enderecoRepository.save(any())).thenReturn(EnderecoDTO.toEntity(laboratorioDTO.getEndereco()));
        when(laboratorioRepository.save(any(Laboratorio.class))).thenReturn(laboratorio);
        when(pessoaRepository.save(any(Pessoa.class))).thenReturn(this.criaAdminComLab());

        LaboratorioDTO result = laboratorioService.create(laboratorioDTO);

        assertNotNull(result);
        verify(laboratorioRepository).save(any(Laboratorio.class));
    }

    @Test
    void testCreate_WithExistingCnpj() {
        LaboratorioCreateRequest laboratorioDTO = criaLaboratorioRequest();
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
        return laboratorioDTO;
    }

    private LaboratorioCreateRequest criaLaboratorioRequest() {
        LaboratorioCreateRequest laboratorioDTO = new LaboratorioCreateRequest();
        laboratorioDTO.setId(1L);
        laboratorioDTO.setNome("Laboratório XYZ");
        laboratorioDTO.setCnpj("12.345.678/0001-90");
        laboratorioDTO.setTelefone("12345-6789");
        laboratorioDTO.setRazaoSocial("Laboratório XYZ LTDA");
        laboratorioDTO.setEmail("contato@laboratorioxyz.com");
        laboratorioDTO.setEndereco(new EnderecoDTO());
        laboratorioDTO.setIdAdm(1L);
        return laboratorioDTO;
    }

    private Pessoa criaAdmin() {
        Pessoa pacienteDTO = new Pessoa();
        pacienteDTO.setId(1L);
        pacienteDTO.setNome("João da Silva");
        pacienteDTO.setCpf("123.456.789-00");
        pacienteDTO.setTelefone("98765-4321");
        pacienteDTO.setSexo("Masculino");
        pacienteDTO.setEmail("joao@example.com");
        pacienteDTO.setSenha("senha123");
        pacienteDTO.setDataNascimento(new Date());
        pacienteDTO.setPerfis(new HashSet<>());
        pacienteDTO.setConvenios(new ArrayList<>());
        pacienteDTO.setProntuarios(new ArrayList<>());
        pacienteDTO.setExamesRealizados(new ArrayList<>());
        return pacienteDTO;
    }

    private Pessoa criaAdminComLab() {
        Pessoa pacienteDTO = new Pessoa();
        pacienteDTO.setId(1L);
        pacienteDTO.setNome("João da Silva");
        pacienteDTO.setCpf("123.456.789-00");
        pacienteDTO.setTelefone("98765-4321");
        pacienteDTO.setSexo("Masculino");
        pacienteDTO.setEmail("joao@example.com");
        pacienteDTO.setSenha("senha123");
        pacienteDTO.setDataNascimento(new Date());
        pacienteDTO.setPerfis(new HashSet<>());
        pacienteDTO.setConvenios(new ArrayList<>());
        pacienteDTO.setProntuarios(new ArrayList<>());
        pacienteDTO.setExamesRealizados(new ArrayList<>());
        pacienteDTO.setLaboratorio(LaboratorioDTO.toEntity(this.criaLaboratorio()));
        return pacienteDTO;
    }
}