package pmgkn.pescores.pescores.validation.common;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import pmgkn.pescores.pescores.repositories.SchoolRepository;

public class UniqueSchoolNameValidator implements ConstraintValidator<UniqueSchoolName, String> {

    private final SchoolRepository schoolRepository;

    public UniqueSchoolNameValidator(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    @Override
    public boolean isValid(String schoolName,
                           ConstraintValidatorContext context) {

        return this.schoolRepository
                .findByName(schoolName)
                .isEmpty();
    }
}
