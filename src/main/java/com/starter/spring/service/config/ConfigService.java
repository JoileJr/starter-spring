package com.starter.spring.service.config;



public interface ConfigService {
    
    void instanciaPerfil();

    void instanciaTipoExame();

    void instanciaParametro();

    Boolean perfisInstanciados();

    Boolean tipoExameInstanciados();

    Boolean parametrosInstanciados();
}
