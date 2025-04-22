package com.br.Cidade.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UFValidators.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidUF {
    String message() default "UF inválida. Use uma sigla válida de estado brasileiro.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
