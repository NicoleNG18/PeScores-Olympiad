package pmgkn.pescores.pescores.validation.user;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import pmgkn.pescores.pescores.repositories.UserRepository;

public class UniqueUserEmailValidator implements ConstraintValidator<UniqueUserEmail, String> {

    private final UserRepository userRepository;

    public UniqueUserEmailValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean isValid(String username,
                           ConstraintValidatorContext context) {

        return this.userRepository.
                findUserEntityByEmail(username).
                isEmpty();
    }
}
