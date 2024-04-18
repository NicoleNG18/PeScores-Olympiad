package pmgkn.pescores.pescores.validation.common;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidClassNameValidator implements ConstraintValidator<ValidClassName, String> {
    @Override
    public boolean isValid(String value,
                           ConstraintValidatorContext context) {

        if (value.length() < 2 || value.length() > 4) {
            return false;
        }

        for (int i = 0; i < value.length(); i++) {
            if (Character.isDigit(value.charAt(i))) {
                return true;
            }
        }

        return false;
    }
}
