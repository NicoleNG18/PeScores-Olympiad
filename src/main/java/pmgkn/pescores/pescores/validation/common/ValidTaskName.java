package pmgkn.pescores.pescores.validation.common;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static pmgkn.pescores.pescores.domain.entity.constants.ValidationErrorMessages.INVALID_TASK;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = ValidTaskNameValidator.class)
public @interface ValidTaskName {
    String message() default INVALID_TASK;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}