package com.starter.spring.enums;

public enum TipoUsuario {
    CEO(""),
    PACIENTE(""),
    ADMINSTRATIVO("Administrativo"),
    MEDICO("Médico"),
    ENFERMEIRO("Enfermeiro"),
    TECNICO_ENFERMAGEM("Técnico de Enfermagem"),
    BIOMEDICO("Biomédico");

    private final String descricao;

    TipoUsuario(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}