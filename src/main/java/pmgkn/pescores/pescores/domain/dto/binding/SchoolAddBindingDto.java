package pmgkn.pescores.pescores.domain.dto.binding;

import pmgkn.pescores.pescores.validation.common.UniqueSchoolName;
import pmgkn.pescores.pescores.validation.common.ValidCityName;
import pmgkn.pescores.pescores.validation.common.ValidSchool;

public class SchoolAddBindingDto {

    @UniqueSchoolName
    @ValidSchool
    private String schoolName;
    @ValidCityName
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
