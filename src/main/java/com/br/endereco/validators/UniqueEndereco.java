package com.br.endereco.validators;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Constraint( validatedBy = UniqueEnderecoValidator.class )
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueEndereco {
    String message() default "Este Endereço já está cadastrado!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
