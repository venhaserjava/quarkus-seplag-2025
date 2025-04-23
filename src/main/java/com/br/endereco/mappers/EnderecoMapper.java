package com.br.endereco.mappers;

import com.br.cidade.entities.Cidade;
import com.br.endereco.dtos.EnderecoRequest;
import com.br.endereco.dtos.EnderecoResponse;
import com.br.endereco.entities.Endereco;

public class EnderecoMapper {

    public static Endereco toEntity(EnderecoRequest request, Cidade cidade){
        Endereco e = new Endereco();
        e.setTipo_logradouro(request.tipoLogradouro());
        e.setLogradouro(request.logradouro());
        e.setNumero(request.numero());
        e.setBairro(request.bairro());
        e.setCidade(cidade);
        return e;
    }

    public static EnderecoResponse toResponse(Endereco e){
        return new EnderecoResponse(
            e.getId(),
         e.getTipo_logradouro(),
         e.getLogradouro(),
         e.getNumero(),
         e.getBairro(),
         e.getCidade().getId()
        );
    }
    public static void updateEntity(Endereco e,EnderecoRequest request,Cidade cidade
    ){
        e.setTipo_logradouro(request.tipoLogradouro());
        e.setLogradouro(request.logradouro());
        e.setNumero(request.numero());
        e.setBairro(request.bairro());
        e.setCidade(cidade);        
    }

}
