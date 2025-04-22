package com.br.Cidade.validators;

import java.util.Set;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UFValidators implements ConstraintValidator<ValidUF,String> {
    private static final Set<String> ESTADOS_VALIDOS = Set.of(
        "AC", "AL", "AM", "AP", "BA", "CE", "DF", "ES", "GO", "MA",
        "MG", "MS", "MT", "PA", "PB", "PE", "PI", "PR", "RJ", "RN",
        "RO", "RR", "RS", "SC", "SE", "SP", "TO"
    );

    @Override
    public boolean isValid(String uf,ConstraintValidatorContext context){
        if(uf == null) return false;
        return ESTADOS_VALIDOS.contains(uf.toUpperCase());
    }

}
