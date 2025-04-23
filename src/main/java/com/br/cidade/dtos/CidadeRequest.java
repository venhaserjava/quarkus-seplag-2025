package com.br.cidade.dtos;

import com.br.cidade.validators.UniqueCidade;
import com.br.cidade.validators.ValidUF;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@UniqueCidade
public record CidadeRequest(

    @NotBlank(message = "O nome da cidade não pode estar em branco.")
    @Size(max = 200,message = "O nome da cidade não pode ter mais de 200 caracteres.")
    String nome,

    @NotBlank(message = "A UF da cidade não pode estar em branco.")
    @Size(min = 2,max = 2,message = "A UF deve ter exatamente 2 caracteres.")
    @ValidUF
    String uf
) {}
