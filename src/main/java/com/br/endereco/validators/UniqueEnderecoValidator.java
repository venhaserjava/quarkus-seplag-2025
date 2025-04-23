package com.br.endereco.validators;

import com.br.cidade.repositories.CidadeRepository;
import com.br.endereco.dtos.EnderecoRequest;
import com.br.endereco.repositories.EnderecoRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@ApplicationScoped
public class UniqueEnderecoValidator implements ConstraintValidator<UniqueEndereco,EnderecoRequest> {

    @Inject
    EnderecoRepository enderecoRepository;

    @Inject
    CidadeRepository cidadeRepository;

    @Override
    public boolean isValid(EnderecoRequest request, ConstraintValidatorContext context) {

        if (request==null || context==null || request.cidadeId()==null) return true;
        
        return !enderecoRepository.existsByLogradouroAndNumeroAndBairroAndCidade(
            request.logradouro(),
            request.numero(),
            request.bairro(),
            request.cidadeId()
            );        
    }
}
