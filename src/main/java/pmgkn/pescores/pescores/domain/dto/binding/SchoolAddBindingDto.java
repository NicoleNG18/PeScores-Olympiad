package pmgkn.pescores.pescores.domain.dto.binding;

import jakarta.validation.constraints.NotEmpty;
import pmgkn.pescores.pescores.validation.common.UniqueSchoolName;

import static pmgkn.pescores.pescores.domain.entity.constants.ValidationErrorMessages.*;

public class SchoolAddBindingDto {

    @NotEmpty(message = SCHOOL_NAME_REQUIRED)
    @UniqueSchoolName
    private String name;

    @NotEmpty(message = CITY_NAME_REQUIRED)
    private String city;

    public SchoolAddBindingDto() {
    }

    public String getName() {
        return name;
    }

    public SchoolAddBindingDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getCity() {
        return city;
    }

    public SchoolAddBindingDto setCity(String city) {
        this.city = city;
        return this;
    }
}
