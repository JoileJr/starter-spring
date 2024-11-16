package com.starter.spring.dto.useCases;


import java.util.Collection;

import com.starter.spring.dto.models.PessoaDTO;

public record LoginResponse(String access_token, Collection<?> authorities, PessoaDTO user) {
}
