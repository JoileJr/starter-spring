package com.starter.spring.dto.models;

import com.starter.spring.model.Endereco;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EnderecoDTO {

    private Long id;

    @NotNull(message = "O campo CEP é requerido")
    private String cep;

    @NotNull(message = "O campo estado é requerido")
    private String estado;

    @NotNull(message = "O campo cidade é requerido")
    private String cidade;
    
    @NotNull(message = "O campo bairro é requerido")
    private String bairro;

    @NotNull(message = "O campo logradouro é requerido")
    private String logradouro;

    @NotNull(message = "O campo número é requerido")
    private String numero;

    private String complemento;

    public static EnderecoDTO toDTO(Endereco endereco) {
        if (endereco == null) {
            return null;
        }

        EnderecoDTO dto = new EnderecoDTO();
        dto.setId(endereco.getId());
        dto.setCep(endereco.getCep());
        dto.setEstado(endereco.getEstado());
        dto.setCidade(endereco.getCidade());
        dto.setBairro(endereco.getBairro());
        dto.setLogradouro(endereco.getLogradouro());
        dto.setNumero(endereco.getNumero());
        dto.setComplemento(endereco.getComplemento());

        return dto;
    }

    public static Endereco toEntity(EnderecoDTO dto) {
        if (dto == null) {
            return null;
        }

        Endereco endereco = new Endereco();
        endereco.setId(dto.getId());
        endereco.setCep(dto.getCep());
        endereco.setEstado(dto.getEstado());
        endereco.setCidade(dto.getCidade());
        endereco.setBairro(dto.getBairro());
        endereco.setLogradouro(dto.getLogradouro());
        endereco.setNumero(dto.getNumero());
        endereco.setComplemento(dto.getComplemento());

        return endereco;
    }
}
