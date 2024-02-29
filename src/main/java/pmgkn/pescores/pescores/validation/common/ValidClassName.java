package pmgkn.pescores.pescores.validation.common;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static pmgkn.pescores.pescores.domain.entity.constants.ValidationErrorMessages.INVALID_CASS_NAME;
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = ValidClassNameValidator.class)
public @interface ValidClassName {
    String message() default INVALID_CASS_NAME;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
