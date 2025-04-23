package com.br.unidade.validators;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy = UniqueUnidadeFieldValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueUnidadeField {

    String message() default "Valor já está em uso neste campo.";
    String fieldName();
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
