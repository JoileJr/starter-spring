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
import org.springframework.util.ResourceUtils;

import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ExamesServiceImpl implements ExamesService {
    private static final String JASPER_DIRETORIO = "classpath:jasper/";
    private static final String JASPER_PREFIXO = "exame-relatorio";
    private static final String JASPER_SUFIXO = ".jasper";
    private static final String JASPER_PARAM = "ExameID";

    private final ExameRepository exameRepository;
    private final ResultadoParametroRepository resultadoParametroRepository;

    @PersistenceContext
    private EntityManager entityManager;

    private final Connection connection;

    private Map<String, Object> params = new HashMap<>();

    @Override
    public void addParams(String key, Object value) {
        this.params.put(key, value);
    }

    @Override
    public byte[] exportarPDF(Long code) {
        byte[] bytes = null;
        try {
            this.addParams(JASPER_PARAM, code);
            File file = ResourceUtils.getFile(JASPER_DIRETORIO
                    .concat(JASPER_PREFIXO)
                    .concat(JASPER_SUFIXO));
            JasperPrint print = JasperFillManager.fillReport(file.getAbsolutePath(), params, connection);
            bytes = JasperExportManager.exportReportToPdf(print);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bytes;
    }

    @Transactional
    @Override
    public ExameDTO criarExame(ExameDTO exameDTO) {
        Exame exame = ExameDTO.toEntity(exameDTO);
        exame.setAtivo(true);
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
    public ExameDTO listarExamesPorID(Long id) {
        Exame exame = this.exameRepository.findById(id).get();
        ExameDTO exameDTO = new ExameDTO();

        List<ResultadoParametro> rps = resultadoParametroRepository.findByExame_IdAndParametro_TipoExame_Id(exame.getId(), exame.getTipoExame().getId());
        List<ResultadoParametroDTO> rpsDTO = rps.stream().map(ResultadoParametroDTO::toDTO).toList();
        exameDTO = ExameDTO.toDTO(exame);
        exameDTO.setResultadoParametros(rpsDTO);
        return exameDTO;
    }

    @Transactional
    @Override
    public List<ResultadoParametroDTO> listarResultados(Long IdExame) {
        List<ResultadoParametro> rps = resultadoParametroRepository.findByExame_Id(IdExame);
        List<ResultadoParametroDTO> rpsDTO = rps.stream().map(ResultadoParametroDTO::toDTO).toList();
        return rpsDTO;
    }

    @Override
    public void excluirExame(Long id) {
        Exame exame = this.exameRepository.findById(id).get();
        exame.setAtivo(false);
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

        predicates.add(cb.equal(exame.get("ativo"), true));

        if (tipoExameId != null) {
            Join<Exame, TipoExame> tipoExame = exame.join("tipoExame");
            predicates.add(cb.equal(tipoExame.get("id"), tipoExameId));
        }

        query.where(predicates.toArray(new Predicate[0]));

        return entityManager.createQuery(query).getResultList();
    }

}
