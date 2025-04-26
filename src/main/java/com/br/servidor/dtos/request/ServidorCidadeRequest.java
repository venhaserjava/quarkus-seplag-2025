package com.br.servidor.dtos.request;

import com.br.cidade.validators.ValidUF;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ServidorCidadeRequest(

    @NotBlank
    @Size(min = 3,max = 200)
    String nome,

    @NotBlank
    @Size(min =2,max = 2 )
    @ValidUF
    String uf
) {}
