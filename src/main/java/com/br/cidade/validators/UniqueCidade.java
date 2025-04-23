package com.br.cidade.validators;

import java.lang.annotation.Documented;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy = UniqueCidadeValidator.class)
public @interface UniqueCidade {
    String message() default "Já existe uma cidade com este nome e UF.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
