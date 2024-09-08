package com.starter.spring.dto;

import com.starter.spring.model.Perfil;

import lombok.*;

@Data
public class PerfilDTO {
    
    private Long id;

    private String nome;

    public static PerfilDTO toDTO(Perfil perfil) {
        if (perfil == null) {
            return null;
        }

        PerfilDTO dto = new PerfilDTO();
        dto.setId(perfil.getId());
        dto.setNome(perfil.getNome());

        return dto;
    }

    public static Perfil toEntity(PerfilDTO dto) {
        if (dto == null) {
            return null;
        }

        Perfil perfil = new Perfil();
        perfil.setId(dto.getId());
        perfil.setNome(dto.getNome());

        return perfil;
    }

}
