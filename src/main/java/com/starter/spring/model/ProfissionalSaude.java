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
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class ProfissionalSaude extends Pessoa {

    @Column(name = "registroProfissional")
    private String registroProfissional;

    @Column(name = "regiao")
    private String regiao;

    @Column(name = "tipoProfissional")
    private String tipoProfissional;

    @OneToMany(mappedBy = "profissionalSaude", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Exame> exames;

}
