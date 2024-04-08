package pmgkn.pescores.pescores.validation.common;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static pmgkn.pescores.pescores.domain.entity.constants.ValidationErrorMessages.UNIQUE_SCHOOL_NAME;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = UniqueSchoolNameValidator.class)
public @interface UniqueSchoolName {

    String message() default UNIQUE_SCHOOL_NAME;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
