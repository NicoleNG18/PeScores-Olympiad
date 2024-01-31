package pmgkn.pescores.pescores.domain.dto.binding;

import pmgkn.pescores.pescores.domain.dto.view.ClassViewDto;

public class StudentAddBindingDto {

    private String studentName;

    private Integer studentNumber;

    private ClassViewDto studentClass;

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

    public ClassViewDto getStudentClass() {
        return studentClass;
    }

    public StudentAddBindingDto setStudentClass(ClassViewDto studentClass) {
        this.studentClass = studentClass;
        return this;
    }
}
