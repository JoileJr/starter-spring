package com.starter.spring.service.exames;

import com.starter.spring.dto.models.ExameDTO;
import com.starter.spring.dto.models.ResultadoParametroDTO;
import com.starter.spring.dto.useCases.FindExamByFilterRequest;
import com.starter.spring.model.Exame;
import com.starter.spring.model.Pessoa;
import com.starter.spring.model.ResultadoParametro;
import com.starter.spring.model.TipoExame;
import com.starter.spring.repository.ExameRepository;
import com.starter.spring.repository.ResultadoParametroRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExamesServiceImpl implements ExamesService {
    private final ExameRepository exameRepository;
    private final ResultadoParametroRepository resultadoParametroRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public ExameDTO criarExame(ExameDTO exameDTO) {
        Exame exame = ExameDTO.toEntity(exameDTO);
        exame = exameRepository.save(exame);

        for (ResultadoParametroDTO resultadoParametro : exameDTO.getResultadoParametros()) {
            resultadoParametro.setExame(ExameDTO.toDTO(exame));
            this.resultadoParametroRepository.save(ResultadoParametroDTO.toEntity(resultadoParametro));
        }

        return ExameDTO.toDTO(exame);
    }

    @Transactional
    @Override
    public List<ExameDTO> listarExames(FindExamByFilterRequest filter) {
        List<Exame> exames = this.findByFilters(filter);
        List<ExameDTO> examesDTO = new ArrayList<>();

        for (Exame exame : exames) {
            List<ResultadoParametro> rps = resultadoParametroRepository
                    .findByExame_IdAndParametro_TipoExame_Id(exame.getId(), exame.getTipoExame().getId());
            List<ResultadoParametroDTO> rpsDTO = rps.stream().map(ResultadoParametroDTO::toDTO).toList();
            ExameDTO dto = ExameDTO.toDTO(exame);
            dto.setResultadoParametros(rpsDTO);
            examesDTO.add(dto);
        }
        return examesDTO;
    }

    @Transactional
    @Override
    public List<ResultadoParametroDTO> listarResultados(Long IdExame) {
        List<ResultadoParametro> rps = resultadoParametroRepository.findByExame_Id(IdExame);
        List<ResultadoParametroDTO> rpsDTO = rps.stream().map(ResultadoParametroDTO::toDTO).toList();
        return rpsDTO;
    }

    private List<Exame> findByFilters(FindExamByFilterRequest filter) {
        String cpf = filter.getCpf();
        Date dataInicio = filter.getDataInicio();
        Date dataFim = filter.getDataFim();
        Long tipoExameId = filter.getTipoExame();

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Exame> query = cb.createQuery(Exame.class);
        Root<Exame> exame = query.from(Exame.class);

        List<Predicate> predicates = new ArrayList<>();

        if (cpf != null && !cpf.isEmpty()) {
            Join<Exame, Pessoa> paciente = exame.join("paciente");
            predicates.add(cb.equal(paciente.get("cpf"), cpf));
        }

        if (dataInicio != null) {
            predicates.add(cb.greaterThanOrEqualTo(exame.get("dataExame"), dataInicio));
        }

        if (dataFim != null) {
            predicates.add(cb.lessThanOrEqualTo(exame.get("dataExame"), dataFim));
        }

        if (tipoExameId != null) {
            Join<Exame, TipoExame> tipoExame = exame.join("tipoExame");
            predicates.add(cb.equal(tipoExame.get("id"), tipoExameId));
        }

        query.where(predicates.toArray(new Predicate[0]));

        return entityManager.createQuery(query).getResultList();
    }

}
