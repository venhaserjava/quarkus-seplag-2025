package com.br.endereco.dtos;

public record EnderecoResponse(
    Long id,
    String tipoLogradouro,
    String logradouro,
    Integer numero,
    String bairro,
    Long cidadeId
)
 {}
