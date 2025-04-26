package com.br.unidade.dtos;

import java.util.HashSet;
import java.util.Set;

import com.br.servidor.dtos.request.ServidorEnderecoRequest;
import com.br.unidade.validators.UniqueUnidadeField;

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
    @UniqueUnidadeField(fieldName = "nome",message = "Este nome já está em uso." )
    private String nome;

    @NotBlank
    @Size(min = 3,max = 20)
    @UniqueUnidadeField(fieldName = "sigla", message = "Esta sigla já está em uso.")
    private String sigla;

    @Builder.Default
    private Set<ServidorEnderecoRequest> enderecos = new HashSet<>();
}
