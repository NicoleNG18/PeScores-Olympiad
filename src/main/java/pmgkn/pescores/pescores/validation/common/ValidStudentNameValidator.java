package pmgkn.pescores.pescores.validation.common;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidStudentNameValidator implements ConstraintValidator<ValidStudentName, String> {
    @Override
    public boolean isValid(String value,
                           ConstraintValidatorContext context) {

        if(value.length()<2 || value.length()>30){
            return false;
        }

        for(int i=0;i<value.length();i++){
            if(!Character.isLetter(value.charAt(i)) && !Character.isWhitespace(value.charAt(i))){
                return false;
            }
        }

        return true;
    }

}
