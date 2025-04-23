package com.br.cidade.mappers;

import com.br.cidade.dtos.CidadeRequest;
import com.br.cidade.dtos.CidadeResponse;
import com.br.cidade.entities.Cidade;

public class CidadeMapper {

    public static Cidade toEntity(CidadeRequest request){
        return Cidade.builder()
            .nome(request.nome())
            .uf(request.uf())
            .build();
    }

    public static CidadeResponse toResponse(Cidade entity){
        if (entity==null) {
            return new CidadeResponse(null, null, null);
        }
        return new CidadeResponse(entity.getId(),entity.getNome(),entity.getUf());
    }

    public static void updateEntity(Cidade entity,CidadeRequest request){
        entity.setNome(request.nome());
        entity.setUf(request.uf().toUpperCase());
    }

}
