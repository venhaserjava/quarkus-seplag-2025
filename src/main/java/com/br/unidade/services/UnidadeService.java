package com.br.unidade.services;

import java.util.List;
import java.util.stream.Collectors;

import com.br.cidade.entities.Cidade;
import com.br.cidade.repositories.CidadeRepository;
import com.br.commons.dtos.PagedResponseDTO;
import com.br.endereco.entities.Endereco;
import com.br.endereco.repositories.EnderecoRepository;
import com.br.unidade.dtos.UnidadeRequest;
import com.br.unidade.dtos.UnidadeResponse;
import com.br.unidade.entities.Unidade;
import com.br.unidade.entities.UnidadeEndereco;
import com.br.unidade.mappers.UnidadeMapper;
import com.br.unidade.repositories.UnidadeEnderecoRepository;
import com.br.unidade.repositories.UnidadeRepository;

import io.quarkus.panache.common.Page;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class UnidadeService {

    @Inject
    UnidadeRepository unidadeRepository;

    @Inject
    CidadeRepository cidadeRepository;

    @Inject
    EnderecoRepository enderecoRepository;

    @Inject
    UnidadeEnderecoRepository unidadeEnderecoRepository;

    @Inject
    UnidadeMapper unidadeMapper;

    @Transactional
    public UnidadeResponse create(UnidadeRequest request) {
        var enderecoDTO = request.getEnderecos().iterator().next();
        var cidadeDTO = enderecoDTO.cidade();
        var cidade = cidadeRepository.findByNomeAndUf(cidadeDTO.nome(),cidadeDTO.uf())
                                        .orElseGet(() -> {
                                            var nova = new Cidade();
                                            nova.setNome(cidadeDTO.nome());
                                            nova.setUf(cidadeDTO.uf());
                                            cidadeRepository.persist(nova);
                                            return nova;
                                        });
        var endereco = enderecoRepository.findByTipoLogradouroAndLogradouroAndNumeroAndBairroAndCidadeId(
//            "tipoLogradouro = ?1 and logradouro = ?2 and numero = ?3 and bairro = ?4 and cidade.id = ?5",            
            enderecoDTO.tipoLogradouro(),
            enderecoDTO.logradouro(),
            enderecoDTO.numero(),
            enderecoDTO.bairro(),
            cidade.getId()
            ).orElseGet(() -> {
                var novo = Endereco.builder()
                            .tipo_logradouro(enderecoDTO.tipoLogradouro())
                            .logradouro(enderecoDTO.logradouro())
                            .numero(enderecoDTO.numero())
                            .bairro(enderecoDTO.bairro())
                            .cidade(cidade)
                            .build();
                enderecoRepository.persist(novo);
                return novo;
            });
        
        var unidade = Unidade.builder()
                .nome(request.getNome())
                .sigla(request.getSigla())
                .build();
        unidadeRepository.persist(unidade);

        var unidadeEndereco =  new UnidadeEndereco(null,unidade,endereco);
        unidadeEnderecoRepository.persist(unidadeEndereco);

        unidade.setUnidadeEndereco(unidadeEndereco);
        return UnidadeMapper.toResponse(unidade);
    }

    public PagedResponseDTO<UnidadeResponse> findAll(int page, int size){
        var query = unidadeRepository.findAll().page(Page.of(page,size));
        List<Unidade> unidades = query.list();

        List<UnidadeResponse> content = unidades.stream()
            .map(unidade ->{
//                Endereco endereco = unidadeEnderecoRepository.findEnderecoByUnidade(unidade);
                return UnidadeMapper.toResponse(unidade);

            })
            .collect(Collectors.toList());
        long totalElements = unidadeRepository.count();

        return new PagedResponseDTO<>(content, page, size, totalElements);
    }

    public UnidadeResponse findById(Long id){
        Unidade unidade = unidadeRepository.findByIdOptional(id)
                            .orElseThrow(() -> new RuntimeException("unidade não encontrada"));
//        Endereco endereco = unidadeEnderecoRepository.findEnderecoByUnidade(unidade);
        return UnidadeMapper.toResponse(unidade);
    }
    @Transactional
    public UnidadeResponse update(Long id, UnidadeRequest request){
        Unidade unidade = unidadeRepository.findByIdOptional(id)
                              .orElseThrow(() -> new RuntimeException("Unidade não encontrada"));
            unidade.setNome(request.getNome());
            unidade.setSigla(request.getSigla());
            unidadeRepository.persist(unidade);
            return UnidadeMapper.toResponse(unidade);        
    }

}
