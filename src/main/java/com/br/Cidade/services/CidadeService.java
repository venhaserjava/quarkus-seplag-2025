package com.br.Cidade.services;


import java.util.List;
import java.util.stream.Collectors;

import com.br.Cidade.dtos.CidadeRequest;
import com.br.Cidade.dtos.CidadeResponse;
import com.br.Cidade.entities.Cidade;
import com.br.Cidade.mappers.CidadeMapper;
import com.br.Cidade.repositories.CidadeRepository;
import com.br.commons.dtos.PagedResponseDTO;
import com.br.commons.exceptions.ResourceNotFoundException;
import io.quarkus.panache.common.Page;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class CidadeService {


    @Inject
    CidadeRepository cidadeRepository;

    @Transactional
    public CidadeResponse create(CidadeRequest request){
        Cidade cidade = CidadeMapper.toEntity(request);
        cidadeRepository.persist(cidade);
        return CidadeMapper.toResponse(cidade);
    }

    public PagedResponseDTO<CidadeResponse> findAll(int page, int size) {
        Page pagedRequest = Page.of(page,size);
        var query = cidadeRepository.findAllCities(page, size).page(pagedRequest);
        List<CidadeResponse> content = query.list()
            .stream()
            .map(CidadeMapper::toResponse)
            .collect(Collectors.toList());
        long totalElements = query.count();
        return new PagedResponseDTO<>(content, page, size, totalElements);
    }

    public CidadeResponse findById(Long id) {
        Cidade cidade = cidadeRepository.findById(id);
        return CidadeMapper.toResponse(cidade);
    }

    @Transactional
    public CidadeResponse update(Long id,CidadeRequest request){
        Cidade cidade = cidadeRepository.findByIdOptional(id)
            .orElseThrow(()-> new NotFoundException("A cidade que possue o ID.: "+id.toString()+" não foi encontrada!"));
            CidadeMapper.updateEntity(cidade, request);
            cidadeRepository.persist(cidade);
            return CidadeMapper.toResponse(cidade);
    }
    
    @Transactional
    public void delete(Long id) {
        Cidade cidade = cidadeRepository.findByIdOptional(id)
            .orElseThrow(()-> new ResourceNotFoundException("A cidade que possue o ID.: "+id.toString()+" não foi encontrada!"));
        cidadeRepository.delete(cidade);    
    }

}
