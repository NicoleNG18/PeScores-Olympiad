package pmgkn.pescores.pescores.validation.common;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import pmgkn.pescores.pescores.validation.product.ValidProductNameValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static pmgkn.pescores.pescores.domain.entity.constants.ValidationErrorMessages.INVALID_SCHOOL_NAME;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = ValidProductNameValidator.class)
public @interface ValidSchool {
    String message() default INVALID_SCHOOL_NAME;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
