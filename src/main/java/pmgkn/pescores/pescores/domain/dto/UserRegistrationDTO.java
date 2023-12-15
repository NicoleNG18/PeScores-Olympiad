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
public class UserRegistrationDTO {

    @ValidPersonName
    private String firstName;
    @ValidPersonName
    private String lastName;
    @ValidEmail
    @UniqueUserEmail(message = EMAIL_UNIQUE)
    private String email;
    private String school;
    @ValidPassword
    private String password;
    private  String confirmPassword;

    public UserRegistrationDTO() {
    }

    public String getFirstName() {
        return firstName;
    }

    public UserRegistrationDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserRegistrationDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserRegistrationDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getSchool() {
        return school;
    }

    public UserRegistrationDTO setSchool(String school) {
        this.school = school;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserRegistrationDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public UserRegistrationDTO setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }
}

