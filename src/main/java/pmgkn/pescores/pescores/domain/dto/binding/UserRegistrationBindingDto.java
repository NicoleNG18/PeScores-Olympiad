package pmgkn.pescores.pescores.domain.dto.binding;

import pmgkn.pescores.pescores.validation.common.ValidEmail;
import pmgkn.pescores.pescores.validation.common.ValidPersonName;
import pmgkn.pescores.pescores.validation.user.FieldMatch;
import pmgkn.pescores.pescores.validation.user.UniqueUserEmail;
import pmgkn.pescores.pescores.validation.user.ValidPassword;

import static pmgkn.pescores.pescores.domain.entity.constants.Messages.CONFIRM_PASSWORD;
import static pmgkn.pescores.pescores.domain.entity.constants.Messages.PASSWORD;
import static pmgkn.pescores.pescores.domain.entity.constants.ValidationErrorMessages.MATCHING_PASSWORDS;

@FieldMatch(firstField = PASSWORD,
        secondField = CONFIRM_PASSWORD,
        message = MATCHING_PASSWORDS)
public class UserRegistrationBindingDto {

    @ValidPersonName
    private String firstName;
    @ValidPersonName
    private String lastName;
    @ValidEmail
    @UniqueUserEmail
    private String email;

    private String school;
    @ValidPassword
    private String password;
    private String confirmPassword;

    public UserRegistrationBindingDto() {
    }

    public String getFirstName() {
        return firstName;
    }

    public UserRegistrationBindingDto setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserRegistrationBindingDto setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserRegistrationBindingDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getSchool() {
        return school;
    }

    public UserRegistrationBindingDto setSchool(String school) {
        this.school = school;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserRegistrationBindingDto setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public UserRegistrationBindingDto setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }
}

