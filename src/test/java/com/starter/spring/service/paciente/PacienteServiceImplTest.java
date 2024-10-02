package com.starter.spring.service.paciente;

import com.starter.spring.dto.models.PacienteDTO;
import com.starter.spring.exceptions.DataIntegrityViolationException;
import com.starter.spring.exceptions.ObjectnotFoundException;
import com.starter.spring.model.Paciente;
import com.starter.spring.model.Pessoa;
import com.starter.spring.repository.PacienteRepository;
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
    private PacienteRepository pacienteRepository;

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
        PacienteDTO pacienteDTO = this.criaPaciente();
        Paciente paciente = PacienteDTO.toEntity(pacienteDTO);
        when(pacienteRepository.findById(id)).thenReturn(Optional.of(paciente));

        PacienteDTO result = pacienteService.findById(id);

        assertNotNull(result);
        verify(pacienteRepository).findById(id);
    }

    @Test
    void testFindAll() {
        PacienteDTO pacienteDTO = this.criaPaciente();
        Paciente paciente = PacienteDTO.toEntity(pacienteDTO);
        when(pacienteRepository.findAll()).thenReturn(Collections.singletonList(paciente));

        List<PacienteDTO> result = pacienteService.findAll();

        assertFalse(result.isEmpty());
        verify(pacienteRepository).findAll();
    }

    @Test
    void testCreate() {
        PacienteDTO pacienteDTO = this.criaPaciente();
        Paciente paciente = PacienteDTO.toEntity(pacienteDTO);

        when(pessoaRepository.findByCpfOrEmail(anyString(), anyString())).thenReturn(Optional.empty());
        when(pacienteRepository.save(any(Paciente.class))).thenReturn(paciente);

        PacienteDTO result = pacienteService.create(pacienteDTO);

        assertNotNull(result);
        verify(pacienteRepository).save(any(Paciente.class));
    }

    @Test
    void testUpdate() {
        PacienteDTO pacienteDTO = this.criaPaciente();
        Paciente paciente = PacienteDTO.toEntity(pacienteDTO);

        when(pacienteRepository.save(any(Paciente.class))).thenReturn(paciente);

        PacienteDTO result = pacienteService.update(pacienteDTO.getId(), pacienteDTO);

        assertNotNull(result);
        verify(pacienteRepository).save(any(Paciente.class));
    }

    @Test
    void testFindById_NotFound() {
        Long id = 1L;
        when(pacienteRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ObjectnotFoundException.class, () -> {
            pacienteService.findById(id);
        });

        verify(pacienteRepository).findById(id);
    }

    @Test
    void testCreate_WithExistingCpfOrEmail() {
        PacienteDTO pacienteDTO = this.criaPaciente();
        when(pessoaRepository.findByCpfOrEmail(anyString(), anyString())).thenReturn(Optional.of(new Pessoa()));

        assertThrows(DataIntegrityViolationException.class, () -> {
            pacienteService.create(pacienteDTO);
        });

        verify(pessoaRepository).findByCpfOrEmail(anyString(), anyString());
        verify(pacienteRepository, never()).save(any(Paciente.class));
    }

    @Test
    void testUpdate_NotFound() {
        Long id = 1L;
        PacienteDTO pacienteDTO = this.criaPaciente();
        pacienteDTO.setId(id);

        when(pacienteRepository.save(any(Paciente.class))).thenThrow(new ObjectnotFoundException("Objeto não encontrado! Id: " + id));

        assertThrows(ObjectnotFoundException.class, () -> {
            pacienteService.update(id, pacienteDTO);
        });

        verify(pacienteRepository).save(any(Paciente.class));
    }

    @Test
    void testUpdate_WithExistingCpfOrEmail() {
        PacienteDTO pacienteDTO = this.criaPaciente();
        when(pessoaRepository.findByCpfOrEmail(anyString(), anyString())).thenReturn(Optional.of(new Pessoa()));

        assertThrows(DataIntegrityViolationException.class, () -> {
            pacienteService.update(pacienteDTO.getId(), pacienteDTO);
        });

        verify(pessoaRepository).findByCpfOrEmail(anyString(), anyString());
        verify(pacienteRepository, never()).save(any(Paciente.class));
    }

    private PacienteDTO criaPaciente() {
        PacienteDTO pacienteDTO = new PacienteDTO();
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
        pacienteDTO.setExames(new ArrayList<>());
        return pacienteDTO;
    }

}