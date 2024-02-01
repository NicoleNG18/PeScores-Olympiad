package pmgkn.pescores.pescores.domain.dto.binding;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import pmgkn.pescores.pescores.domain.enums.GenderEnum;
import pmgkn.pescores.pescores.validation.common.ValidStudentName;

public class StudentAddBindingDto {
    @ValidStudentName
    private String studentName;
    @Positive
    private Integer studentNumber;

    private String studentClass;

    @Enumerated(EnumType.STRING)
    @NotNull
    private GenderEnum gender;

    public StudentAddBindingDto() {
    }

    public GenderEnum getGender() {
        return gender;
    }

    public StudentAddBindingDto setGender(GenderEnum gender) {
        this.gender = gender;
        return this;
    }

    public String getStudentName() {
        return studentName;
    }

    public StudentAddBindingDto setStudentName(String studentName) {
        this.studentName = studentName;
        return this;
    }

    public Integer getStudentNumber() {
        return studentNumber;
    }

    public StudentAddBindingDto setStudentNumber(Integer studentNumber) {
        this.studentNumber = studentNumber;
        return this;
    }

    public String getStudentClass() {
        return studentClass;
    }

    public StudentAddBindingDto setStudentClass(String studentClass) {
        this.studentClass = studentClass;
        return this;
    }
}
