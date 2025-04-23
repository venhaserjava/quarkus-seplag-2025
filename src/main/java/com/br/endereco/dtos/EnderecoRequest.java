package com.br.endereco.dtos;

import com.br.endereco.validators.UniqueEndereco;

import io.smallrye.common.constraint.NotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@UniqueEndereco
public record EnderecoRequest(
    
    @NotBlank
    @Size(max = 50)
    String tipoLogradouro,

    @NotBlank
    @Size(max = 200)
    String logradouro,

    @NotNull    
    Integer numero,

    @NotBlank
    @Size(max = 100)
    String bairro,

    @NotNull
    Long cidadeId
) {

}
