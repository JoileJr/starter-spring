package com.starter.spring.model;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import com.starter.spring.enums.TipoUsuario;
import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@DiscriminatorValue("profissionalSaude")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class ProfissionalSaude extends Pessoa {

    @Column(name = "registroProfissional")
    private String registroProfissional;

    @Column(name = "regiao")
    private String regiao;

    @Column(name = "tipoProfissional")
    private TipoUsuario tipoProfissional;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "laboratorio_id")
    private Laboratorio laboratorio;

    @OneToMany(mappedBy = "profissionalSaude", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Exame> exames;

}
