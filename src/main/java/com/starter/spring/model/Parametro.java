package com.starter.spring.model;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "parametro")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Parametro {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "unidadeDeMedida")
    private String unidadeDeMedida;

    @Column(name = "valorReferenciaMinimo")
    private String valorReferenciaMinimo;

    @Column(name = "valorReferenciaMaximo")
    private String valorReferenciaMaximo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipoExame_id")
    private TipoExame tipoExame;

    @OneToMany(mappedBy = "parametro", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ResultadoParametro> ResultadoParametros;
    
}
