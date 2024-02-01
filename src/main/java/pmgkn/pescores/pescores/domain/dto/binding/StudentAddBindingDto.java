package pmgkn.pescores.pescores.domain.dto.binding;

import jakarta.validation.constraints.Positive;
import pmgkn.pescores.pescores.validation.common.ValidPersonName;

public class StudentAddBindingDto {
    @ValidPersonName
    private String studentName;
    @Positive
    private Integer studentNumber;

    private String studentClass;

    public StudentAddBindingDto() {
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
