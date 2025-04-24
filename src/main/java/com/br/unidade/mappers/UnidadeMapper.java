package com.br.unidade.mappers;

import com.br.unidade.dtos.UnidadeRequest;
import com.br.unidade.dtos.UnidadeResponse;
import com.br.unidade.entities.Unidade;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UnidadeMapper {

    public static Unidade toEntity(UnidadeRequest request) {        
        Unidade u = new Unidade();
        u.setNome(request.getNome());
        u.setSigla(request.getSigla());
        return u;
    }

    public static UnidadeResponse toResponse(Unidade u){
        return new UnidadeResponse(u.getId(),u.getNome(),u.getSigla());
    }

    public static void updateEntity(Unidade u, UnidadeRequest request){
        u.setNome(request.getNome());
        u.setSigla(request.getSigla());
    }

}
