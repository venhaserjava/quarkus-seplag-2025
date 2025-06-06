package com.br.cidade.validators;

import com.br.cidade.dtos.CidadeRequest;
import com.br.cidade.repositories.CidadeRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@ApplicationScoped
public class UniqueCidadeValidator implements ConstraintValidator<UniqueCidade,CidadeRequest>{
    @Inject
    CidadeRepository cidadeRepository;

    @Override
    public boolean isValid(CidadeRequest request, ConstraintValidatorContext context){
        if (request==null) {
            return true;
        }
        return !cidadeRepository.existsByNomeAndUf(request.nome(),request.uf());
    }

}
