package pmgkn.pescores.pescores.validation.user;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static pmgkn.pescores.pescores.domain.entity.constants.ValidationErrorMessages.UNIQUE_EMAIL;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = UniqueUserEmailValidator.class)
public @interface UniqueUserEmail {

    String message() default UNIQUE_EMAIL;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
