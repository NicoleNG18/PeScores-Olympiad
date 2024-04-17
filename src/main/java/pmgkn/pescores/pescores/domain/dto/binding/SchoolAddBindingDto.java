package pmgkn.pescores.pescores.domain.dto.binding;

import jakarta.validation.constraints.NotEmpty;
import pmgkn.pescores.pescores.validation.common.UniqueSchoolName;
import pmgkn.pescores.pescores.validation.common.ValidSchool;

import static pmgkn.pescores.pescores.domain.entity.constants.ValidationErrorMessages.*;

public class SchoolAddBindingDto {

    @UniqueSchoolName
    @ValidSchool
    private String schoolName;

    @NotEmpty(message = CITY_NAME_REQUIRED)
    private String city;

    public SchoolAddBindingDto() {
    }

    public String getCity() {
        return city;
    }

    public SchoolAddBindingDto setCity(String city) {
        this.city = city;
        return this;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public SchoolAddBindingDto setSchoolName(String schoolName) {
        this.schoolName = schoolName;
        return this;
    }
}
