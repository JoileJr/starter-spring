package com.starter.spring.model;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@DiscriminatorValue("administrativo")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Administrativo extends Pessoa {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "laboratorio_id")
    private Laboratorio laboratorio;

}
