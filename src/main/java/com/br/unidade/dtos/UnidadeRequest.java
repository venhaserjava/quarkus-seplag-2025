package com.br.unidade.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UnidadeRequest {
    @NotBlank
    @Size(min = 3,max = 200)
    private String nome;

    @NotBlank
    @Size(min = 3,max = 20)
    private String sigla;
}
