package com.starter.spring.enums;

public enum TipoExameEnum {
    SANGUE("Sangue"),
    URINA("Urina");

    private final String descricao;

    TipoExameEnum(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}    

