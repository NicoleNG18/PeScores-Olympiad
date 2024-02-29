package pmgkn.pescores.pescores.validation.common;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidClassNameValidator implements ConstraintValidator<ValidClassName, String> {
    @Override
    public boolean isValid(String value,
                           ConstraintValidatorContext context) {

        for (int i = 0; i < value.length(); i++) {
            if (Character.isDigit(value.charAt(i))) {
                return true;
            }
        }

        return false;
    }
}
