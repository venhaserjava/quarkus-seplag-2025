package com.br.endereco.services;

import java.util.List;
import java.util.stream.Collectors;

import com.br.cidade.repositories.CidadeRepository;
import com.br.commons.dtos.PagedResponseDTO;
import com.br.endereco.dtos.EnderecoRequest;
import com.br.endereco.dtos.EnderecoResponse;
import com.br.endereco.entities.Endereco;
import com.br.endereco.mappers.EnderecoMapper;
import com.br.endereco.repositories.EnderecoRepository;

import io.quarkus.panache.common.Page;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;
    private final CidadeRepository cidadeRepository;

    public EnderecoService(EnderecoRepository enderecoRepository,CidadeRepository cidadeRepository){

        this.enderecoRepository = enderecoRepository;
        this.cidadeRepository = cidadeRepository;

    }

    @Transactional
    public EnderecoResponse create(EnderecoRequest request){

        var cidade = cidadeRepository.findByIdOptional(request.cidadeId())
            .orElseThrow(() -> new NotFoundException("Cidade não Encontrada !"));

        Endereco endereco = EnderecoMapper.toEntity(request,cidade);

        enderecoRepository.persist(endereco);

        return EnderecoMapper.toResponse(endereco);
    }

    @Transactional
    public EnderecoResponse update(Long id, EnderecoRequest request){

        var endereco = enderecoRepository.findByIdOptional(id)
            .orElseThrow(()-> new NotFoundException("Endereco não Encontrado !"));
            
        var cidade = cidadeRepository.findByIdOptional(request.cidadeId())
            .orElseThrow(() -> new NotFoundException("Cidade não Encontrada !"));

        EnderecoMapper.updateEntity(endereco, request,cidade);

        enderecoRepository.persist(endereco);

        return EnderecoMapper.toResponse(endereco);
    }

    public EnderecoResponse findById(Long id) {
        return EnderecoMapper.toResponse(enderecoRepository.findByIdOptional(id)
            .orElseThrow(()-> new NotFoundException("Endereço não Encontrado!"))
        );
    }

    public PagedResponseDTO<EnderecoResponse> findAll(int page,int size){

        var query = enderecoRepository.findAll().page(Page.of(page,size));

        List<EnderecoResponse> content = query.list()
            .stream()
            .map(EnderecoMapper::toResponse)
            .collect(Collectors.toList());
        long totalElements = query.count();

        return new PagedResponseDTO<>(content,page,size,totalElements);
    }


}
