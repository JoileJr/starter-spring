package com.starter.spring.model;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@DiscriminatorValue("enfermeiro")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Enfermeiro extends Pessoa {

    @Column(name = "coren")
    private String coren;

    @Column(name = "regiao")
    private String regiao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "laboratorio_id")
    private Laboratorio laboratorio;

    @OneToMany(mappedBy = "enfermeiro", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Exame> exames;

}
