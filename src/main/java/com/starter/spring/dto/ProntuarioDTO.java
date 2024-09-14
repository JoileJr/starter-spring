package com.starter.spring.dto;

import java.util.Date;

import com.starter.spring.model.Prontuario;

import lombok.Data;

@Data
public class ProntuarioDTO {
    
    private Integer id;

    private String historicoPessoal;

    private String historicoFamiliar;

    private String historicoSocial;

    private String anamnese;

    private String diagnostico;

    private String planoTratamento;

    private String resumoAlta;

    private String orientacoesPosAlta;

    private String observacoesFinais;

    private Date dataProntuario;

    private Float peso;

    private Float altura;

    public static ProntuarioDTO toDTO(Prontuario prontuario) {
        if (prontuario == null) {
            return null;
        }

        ProntuarioDTO dto = new ProntuarioDTO();
        dto.setId(prontuario.getId());
        dto.setHistoricoPessoal(prontuario.getHistoricoPessoal());
        dto.setHistoricoFamiliar(prontuario.getHistoricoFamiliar());
        dto.setHistoricoSocial(prontuario.getHistoricoSocial());
        dto.setAnamnese(prontuario.getAnamnese());
        dto.setDiagnostico(prontuario.getDiagnostico());
        dto.setPlanoTratamento(prontuario.getPlanoTratamento());
        dto.setResumoAlta(prontuario.getResumoAlta());
        dto.setOrientacoesPosAlta(prontuario.getOrientacoesPosAlta());
        dto.setObservacoesFinais(prontuario.getObservacoesFinais());
        dto.setDataProntuario(prontuario.getDataProntuario());
        dto.setPeso(prontuario.getPeso());
        dto.setAltura(prontuario.getAltura());

        return dto;
    }

    public static Prontuario toEntity(ProntuarioDTO dto) {
        if (dto == null) {
            return null;
        }

        Prontuario prontuario = new Prontuario();
        prontuario.setId(dto.getId());
        prontuario.setHistoricoPessoal(dto.getHistoricoPessoal());
        prontuario.setHistoricoFamiliar(dto.getHistoricoFamiliar());
        prontuario.setHistoricoSocial(dto.getHistoricoSocial());
        prontuario.setAnamnese(dto.getAnamnese());
        prontuario.setDiagnostico(dto.getDiagnostico());
        prontuario.setPlanoTratamento(dto.getPlanoTratamento());
        prontuario.setResumoAlta(dto.getResumoAlta());
        prontuario.setOrientacoesPosAlta(dto.getOrientacoesPosAlta());
        prontuario.setObservacoesFinais(dto.getObservacoesFinais());
        prontuario.setDataProntuario(dto.getDataProntuario());
        prontuario.setPeso(dto.getPeso());
        prontuario.setAltura(dto.getAltura());

        return prontuario;
    }

}
