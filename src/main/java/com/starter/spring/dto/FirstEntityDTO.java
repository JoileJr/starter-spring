package com.starter.spring.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class FirstEntityDTO {
    private Long id;
    private String username;
    private String email;
}
