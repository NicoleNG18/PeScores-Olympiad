package pmgkn.pescores.pescores.validation.common;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidTaskNameValidator implements ConstraintValidator<ValidTaskName, String> {
    @Override
    public boolean isValid(String value,
                           ConstraintValidatorContext context) {

        if (value.length() < 2 || value.length() > 35) {
            return false;
        }

        return true;
    }
}
