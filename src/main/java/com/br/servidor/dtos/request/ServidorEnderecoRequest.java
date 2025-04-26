package com.br.servidor.dtos.request;

import io.smallrye.common.constraint.NotNull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ServidorEnderecoRequest(

    @NotBlank
    @Size(max = 50)
    @Pattern(regexp = "Rua|Avenida|Travessa|Alameda|Praça|Rodovia|Estrada|Aeroporto|Campo|Chácara|Colônia|Condomínio|Conjunto|Distrito|Esplanada|Estação|Fazenda")
    String tipoLogradouro,

    @NotBlank
    @Size(max = 200)
    String logradouro,

    @NotNull
    @Min(1) int numero,

    @NotBlank
    @Size(max = 100) 
    String bairro,

    @NotNull
    ServidorCidadeRequest cidade
) {}
