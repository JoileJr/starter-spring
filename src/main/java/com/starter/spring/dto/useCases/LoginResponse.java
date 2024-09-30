package com.starter.spring.dto.useCases;


import java.util.Collection;

public record LoginResponse(String token, Collection<?> authorities) {
}
