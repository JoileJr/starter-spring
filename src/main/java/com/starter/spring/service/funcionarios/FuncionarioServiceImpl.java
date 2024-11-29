package com.starter.spring.service.funcionarios;

import com.starter.spring.dto.models.ProfissionalSaudeDTO;
import com.starter.spring.dto.useCases.FilterHealthProfessionalRequest;
import com.starter.spring.dto.useCases.ProfissionalSaudeRequest;
import com.starter.spring.enums.TipoUsuario;
import com.starter.spring.exceptions.DataIntegrityViolationException;
import com.starter.spring.exceptions.ObjectnotFoundException;
import com.starter.spring.model.ProfissionalSaude;
import com.starter.spring.model.Perfil;
import com.starter.spring.model.Pessoa;
import com.starter.spring.repository.EnfermeiroRepository;
import com.starter.spring.repository.PerfilRepository;
import com.starter.spring.repository.PessoaRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FuncionarioServiceImpl implements FuncionarioService {

    private final EnfermeiroRepository enfermeiroRepository;

    private final PessoaRepository pessoaRepository;

    private final PerfilRepository perfilRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public ProfissionalSaudeDTO findById(Long id) {
        Optional<ProfissionalSaude> obj = enfermeiroRepository.findById(id);
        return ProfissionalSaudeDTO
                .toDTO(obj.orElseThrow(() -> new ObjectnotFoundException("Objeto não encontrado! Id: " + id)));
    }

    @Override
    public List<ProfissionalSaudeDTO> findAll() {
        List<ProfissionalSaude> enfermeiros = enfermeiroRepository.findAll();
        return enfermeiros.stream()
                .map(ProfissionalSaudeDTO::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public ProfissionalSaudeDTO create(ProfissionalSaudeRequest objDTO) {
        validateByEmailAndCpf(objDTO);
        Set<Perfil> perfis = getDefaultProfiles(objDTO.getPerfis());
        ProfissionalSaude enfermeiro = ProfissionalSaudeRequest.toEntity(objDTO);
        enfermeiro.setPerfis(perfis);
        enfermeiro = enfermeiroRepository.save(enfermeiro);
        return ProfissionalSaudeDTO.toDTO(enfermeiro);
    }

    @Transactional
    @Override
    public ProfissionalSaudeDTO update(Long id, ProfissionalSaudeRequest objDTO) {
        Optional<Pessoa> pessoaExistente = pessoaRepository.findById(objDTO.getId());
    
        ProfissionalSaude profissionalSaude = ProfissionalSaudeRequest.toEntity(objDTO);;
    
        if (pessoaExistente.isPresent()) {
            profissionalSaude.setId(pessoaExistente.get().getId());
            profissionalSaude.setSenha(pessoaExistente.get().getSenha());
        } 
    
        Set<Perfil> perfis = getDefaultProfiles(objDTO.getPerfis());
        profissionalSaude.setPerfis(perfis);
    
        profissionalSaude = enfermeiroRepository.save(profissionalSaude);
    
        return ProfissionalSaudeDTO.toDTO(profissionalSaude);
    }
    

    private void validateByEmailAndCpf(ProfissionalSaudeRequest objDTO) {
        Optional<Pessoa> obj = pessoaRepository.findByCpfOrEmail(objDTO.getCpf(), objDTO.getEmail());
        if (obj.isPresent()) {
            throw new DataIntegrityViolationException("CPF ou E-mail já cadastrado no sistema!");
        }
    }

    private Set<Perfil> getDefaultProfiles(String perfisBuscar) {
        Set<Perfil> perfis = new HashSet<>();
        Perfil perfil;
        if (perfisBuscar == TipoUsuario.BIOMEDICO.getDescricao()) {
            perfil = perfilRepository.findByNome(TipoUsuario.BIOMEDICO.getDescricao());
        } else if (perfisBuscar == TipoUsuario.ENFERMEIRO.getDescricao()) {
            perfil = perfilRepository.findByNome(TipoUsuario.ENFERMEIRO.getDescricao());
        } else if (perfisBuscar == TipoUsuario.TECNICO_ENFERMAGEM.getDescricao()) {
            perfil = perfilRepository.findByNome(TipoUsuario.TECNICO_ENFERMAGEM.getDescricao());
        } else if (perfisBuscar == TipoUsuario.MEDICO.getDescricao()) {
            perfil = perfilRepository.findByNome(TipoUsuario.MEDICO.getDescricao());
        } else {
            perfil = perfilRepository.findByNome(TipoUsuario.PACIENTE.getDescricao());
        }
        if (perfil != null) {
            perfis.add(perfil);
        }
        return perfis;
    }

    @Override
    public List<ProfissionalSaudeDTO> findByFilter(FilterHealthProfessionalRequest obj) {
        String nome = obj.getNome();
        String cpf = obj.getCpf();
        String email = obj.getEmail();
        String telefone = obj.getTelefone();
        String tipoProfissional = obj.getTipoProfissional();
        String registroProfissional = obj.getRegistroProfissional();
        Date dataInicio = obj.getDataInicio();
        Date dataFim = obj.getDataFim();

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<ProfissionalSaude> query = cb.createQuery(ProfissionalSaude.class);
        Root<ProfissionalSaude> root = query.from(ProfissionalSaude.class);

        List<Predicate> predicates = new ArrayList<>();

        if (nome != null && !nome.isEmpty()) {
            predicates.add(cb.like(cb.lower(root.get("nome")), "%" + nome.toLowerCase() + "%"));
        }

        if (email != null && !email.isEmpty()) {
            predicates.add(cb.like(cb.lower(root.get("email")), "%" + email.toLowerCase() + "%"));
        }

        if (registroProfissional != null && !registroProfissional.isEmpty()) {
            predicates.add(cb.like(cb.lower(root.get("registroProfissional")), "%" + registroProfissional.toLowerCase() + "%"));
        }

        if (cpf != null && !cpf.isEmpty()) {
            predicates.add(cb.equal(root.get("cpf"), cpf));
        }

        if (tipoProfissional != null && !tipoProfissional.isEmpty()) {
            predicates.add(cb.equal(root.get("tipoProfissional"), tipoProfissional));
        }

        if (telefone != null && !telefone.isEmpty()) {
            predicates.add(cb.equal(root.get("telefone"), telefone));
        }

        if (dataInicio != null) {
            predicates.add(cb.greaterThanOrEqualTo(root.get("dataNascimento"), dataInicio));
        }

        if (dataFim != null) {
            predicates.add(cb.lessThanOrEqualTo(root.get("dataNascimento"), dataFim));
        }

        if (!predicates.isEmpty()) {
            query.where(cb.and(predicates.toArray(new Predicate[0])));
        }

        List<ProfissionalSaude> pessoas = entityManager.createQuery(query).getResultList();

        return pessoas.stream()
                .map(ProfissionalSaudeDTO::toDTO)
                .collect(Collectors.toList());
    }

}
