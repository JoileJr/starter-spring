package com.starter.spring.service.paciente;

import com.starter.spring.dto.models.PessoaDTO;
import com.starter.spring.exceptions.DataIntegrityViolationException;
import com.starter.spring.exceptions.ObjectnotFoundException;
import com.starter.spring.model.Pessoa;
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

class PacienteServiceImplTest {

    @InjectMocks
    private PacienteServiceImpl pacienteService;

    @Mock
    private PessoaRepository pessoaRepository;

    @Mock
    private PerfilRepository perfilRepository;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindById() {
        Long id = 1L;
        PessoaDTO pacienteDTO = this.criaPaciente();
        Pessoa paciente = PessoaDTO.toEntity(pacienteDTO);
        when(pessoaRepository.findById(id)).thenReturn(Optional.of(paciente));

        PessoaDTO result = pacienteService.findById(id);

        assertNotNull(result);
        verify(pessoaRepository).findById(id);
    }

    @Test
    void testFindAll() {
        PessoaDTO pacienteDTO = this.criaPaciente();
        Pessoa paciente = PessoaDTO.toEntity(pacienteDTO);
        when(pessoaRepository.findAll()).thenReturn(Collections.singletonList(paciente));

        List<PessoaDTO> result = pacienteService.findAll();

        assertFalse(result.isEmpty());
        verify(pessoaRepository).findAll();
    }

    @Test
    void testCreate() {
        PessoaDTO pacienteDTO = this.criaPaciente();
        Pessoa paciente = PessoaDTO.toEntity(pacienteDTO);

        when(pessoaRepository.findByCpfOrEmail(anyString(), anyString())).thenReturn(Optional.empty());
        when(pessoaRepository.save(any(Pessoa.class))).thenReturn(paciente);

        PessoaDTO result = pacienteService.create(pacienteDTO);

        assertNotNull(result);
        verify(pessoaRepository).save(any(Pessoa.class));
    }

    @Test
    void testUpdate() {
        PessoaDTO pacienteDTO = this.criaPaciente();
        Pessoa paciente = PessoaDTO.toEntity(pacienteDTO);

        when(pessoaRepository.save(any(Pessoa.class))).thenReturn(paciente);

        PessoaDTO result = pacienteService.update(pacienteDTO.getId(), pacienteDTO);

        assertNotNull(result);
        verify(pessoaRepository).save(any(Pessoa.class));
    }

    @Test
    void testFindById_NotFound() {
        Long id = 1L;
        when(pessoaRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ObjectnotFoundException.class, () -> {
            pacienteService.findById(id);
        });

        verify(pessoaRepository).findById(id);
    }

    @Test
    void testCreate_WithExistingCpfOrEmail() {
        PessoaDTO pacienteDTO = this.criaPaciente();
        when(pessoaRepository.findByCpfOrEmail(anyString(), anyString())).thenReturn(Optional.of(new Pessoa()));

        assertThrows(DataIntegrityViolationException.class, () -> {
            pacienteService.create(pacienteDTO);
        });

        verify(pessoaRepository).findByCpfOrEmail(anyString(), anyString());
        verify(pessoaRepository, never()).save(any(Pessoa.class));
    }

    @Test
    void testUpdate_NotFound() {
        Long id = 1L;
        PessoaDTO pacienteDTO = this.criaPaciente();
        pacienteDTO.setId(id);

        when(pessoaRepository.save(any(Pessoa.class))).thenThrow(new ObjectnotFoundException("Objeto não encontrado! Id: " + id));

        assertThrows(ObjectnotFoundException.class, () -> {
            pacienteService.update(id, pacienteDTO);
        });

        verify(pessoaRepository).save(any(Pessoa.class));
    }

    @Test
    void testUpdate_WithExistingCpfOrEmail() {
        PessoaDTO pacienteDTO = this.criaPaciente();
        when(pessoaRepository.findByCpfOrEmail(anyString(), anyString())).thenReturn(Optional.of(new Pessoa()));

        assertThrows(DataIntegrityViolationException.class, () -> {
            pacienteService.update(pacienteDTO.getId(), pacienteDTO);
        });

        verify(pessoaRepository).findByCpfOrEmail(anyString(), anyString());
        verify(pessoaRepository, never()).save(any(Pessoa.class));
    }

    private PessoaDTO criaPaciente() {
        PessoaDTO pacienteDTO = new PessoaDTO();
        pacienteDTO.setId(1L);
        pacienteDTO.setNome("João da Silva");
        pacienteDTO.setCpf("123.456.789-00");
        pacienteDTO.setTelefone("98765-4321");
        pacienteDTO.setSexo("Masculino");
        pacienteDTO.setEmail("joao@example.com");
        pacienteDTO.setSenha("senha123");
        pacienteDTO.setDataNascimento(new Date());
        pacienteDTO.setPerfis(new HashSet<>());
        return pacienteDTO;
    }

}