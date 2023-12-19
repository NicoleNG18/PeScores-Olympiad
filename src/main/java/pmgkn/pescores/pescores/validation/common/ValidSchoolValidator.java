package pmgkn.pescores.pescores.validation.common;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidSchoolValidator implements ConstraintValidator<ValidSchool, String> {
    @Override
    public boolean isValid(String schoolName,
                           ConstraintValidatorContext context) {

        if (schoolName.length() < 15) {
            return false;
        }

        for (int i = 0; i < schoolName.length(); i++) {
            if (!Character.isLetter(schoolName.charAt(i)) && !Character.isWhitespace(schoolName.charAt(i))
                    && !Character.isDigit(schoolName.charAt(i)) && !Character.isValidCodePoint(46)
                    &&!Character.isValidCodePoint(34)) {
                return false;
            }
        }

        return true;
    }
}
