package pmgkn.pescores.pescores.domain.dto;

import pmgkn.pescores.pescores.validation.common.ValidEmail;
import pmgkn.pescores.pescores.validation.common.ValidPersonName;
import pmgkn.pescores.pescores.validation.user.FieldMatch;
import pmgkn.pescores.pescores.validation.user.UniqueUserEmail;
import pmgkn.pescores.pescores.validation.user.ValidPassword;

import static pmgkn.pescores.pescores.domain.entity.constants.Messages.CONFIRM_PASSWORD;
import static pmgkn.pescores.pescores.domain.entity.constants.Messages.PASSWORD;
import static pmgkn.pescores.pescores.domain.entity.constants.ValidationErrorMessages.EMAIL_UNIQUE;
import static pmgkn.pescores.pescores.domain.entity.constants.ValidationErrorMessages.MATCHING_PASSWORDS;

@FieldMatch(firstField = PASSWORD,
        secondField = CONFIRM_PASSWORD,
        message = MATCHING_PASSWORDS)
public record UserRegistrationDTO(@ValidPersonName String firstName,
                                  @ValidPersonName String lastName,
                                  @ValidEmail
                                  @UniqueUserEmail(message = EMAIL_UNIQUE) String email,
                                  String school,
                                  @ValidPassword String password,
                                  String confirmPassword) {

}

