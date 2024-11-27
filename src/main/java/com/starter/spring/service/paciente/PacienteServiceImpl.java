package com.starter.spring.service.paciente;

import java.util.*;
import java.util.stream.Collectors;

import com.starter.spring.dto.models.PessoaDTO;
import com.starter.spring.dto.useCases.FilterPersonsRequest;
import com.starter.spring.enums.TipoUsuario;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.starter.spring.exceptions.DataIntegrityViolationException;
import com.starter.spring.exceptions.ObjectnotFoundException;
import com.starter.spring.model.Perfil;
import com.starter.spring.model.Pessoa;
import com.starter.spring.repository.PerfilRepository;
import com.starter.spring.repository.PessoaRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PacienteServiceImpl implements PacienteService {

    private final PessoaRepository pessoaRepository;

    private final PerfilRepository perfilRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public PessoaDTO findById(Long id) {
        Optional<Pessoa> obj = pessoaRepository.findById(id);
        return PessoaDTO.toDTO(obj.orElseThrow(() -> new ObjectnotFoundException("Objeto não encontrado! Id: " + id)));
    }

    @Override
    public PessoaDTO findByCpf(String cpf) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Pessoa> query = cb.createQuery(Pessoa.class);
        Root<Pessoa> root = query.from(Pessoa.class);

        List<Predicate> predicates = new ArrayList<>();

        if (cpf != null && !cpf.isEmpty()) {
            predicates.add(cb.equal(root.get("cpf"), cpf));
        }

        if (!predicates.isEmpty()) {
            query.where(cb.and(predicates.toArray(new Predicate[0])));
        }

        try {
            Pessoa pessoa = entityManager.createQuery(query).getSingleResult();
            return PessoaDTO.toDTO(pessoa);
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<PessoaDTO> findAll() {
        List<Pessoa> pacientes = pessoaRepository.findAll();
        return pacientes.stream()
                .map(PessoaDTO::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PessoaDTO> findByFilter(FilterPersonsRequest obj) {
        String nome = obj.getNome();
        String cpf = obj.getCpf();
        Date dataInicio = obj.getDataInicio();
        Date dataFim = obj.getDataFim();

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Pessoa> query = cb.createQuery(Pessoa.class);
        Root<Pessoa> root = query.from(Pessoa.class);

        List<Predicate> predicates = new ArrayList<>();

        if (nome != null && !nome.isEmpty()) {
            predicates.add(cb.like(cb.lower(root.get("nome")), "%" + nome.toLowerCase() + "%"));
        }

        if (cpf != null && !cpf.isEmpty()) {
            predicates.add(cb.equal(root.get("cpf"), cpf));
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

        List<Pessoa> pessoas = entityManager.createQuery(query).getResultList();

        return pessoas.stream()
                .map(PessoaDTO::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public PessoaDTO create(PessoaDTO objDTO) {
        validateByEmailAndCpf(objDTO);
        Set<Perfil> perfis = getDefaultProfiles();
        if (objDTO.getSenha() != null) {
            objDTO.setSenha(new BCryptPasswordEncoder().encode(objDTO.getSenha()));
        }
        Pessoa paciente = PessoaDTO.toEntity(objDTO);
        paciente.setPerfis(perfis);
        paciente = pessoaRepository.save(paciente);
        return PessoaDTO.toDTO(paciente);
    }

    @Transactional
    @Override
    public PessoaDTO update(Long Id, PessoaDTO objDTO) {
        objDTO.setId(Id);
        Set<Perfil> perfis = getDefaultProfiles();
        Pessoa paciente = PessoaDTO.toEntity(objDTO);
        paciente.setPerfis(perfis);
        paciente = pessoaRepository.save(paciente);
        return PessoaDTO.toDTO(paciente);
    }

    private void validateByEmailAndCpf(PessoaDTO objDTO) {
        Optional<Pessoa> obj = pessoaRepository.findByCpfOrEmail(objDTO.getCpf(), objDTO.getEmail());
        if (obj.isPresent()) {
            throw new DataIntegrityViolationException("CPF ou E-mail já cadastrado no sistema!");
        }
    }

    private Set<Perfil> getDefaultProfiles() {
        Set<Perfil> perfis = new HashSet<>();
        Perfil perfil = perfilRepository.findByNome(TipoUsuario.PACIENTE.getDescricao());
        if (perfil != null) {
            perfis.add(perfil);
        }
        return perfis;
    }
}
