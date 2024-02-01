package pmgkn.pescores.pescores.domain.dto.view;

import pmgkn.pescores.pescores.domain.entity.ClassEntity;
import pmgkn.pescores.pescores.domain.entity.UserEntity;


import java.math.BigDecimal;
import java.util.UUID;

public class StudentViewDto {

    private UUID id;

    private String studentName;

    private Integer studentNumber;

    private BigDecimal averageGrade;

    private UserEntity teacher;

    private ClassEntity studentClass;

    private BigDecimal thirtyMeters;
    private BigDecimal twoHundredMeters;
    private BigDecimal tTest;
    private BigDecimal denseBall;
    private BigDecimal jump;

    public StudentViewDto() {
    }

    public UUID getId() {
        return id;
    }

    public StudentViewDto setId(UUID id) {
        this.id = id;
        return this;
    }

    public String getStudentName() {
        return studentName;
    }

    public StudentViewDto setStudentName(String studentName) {
        this.studentName = studentName;
        return this;
    }

    public Integer getStudentNumber() {
        return studentNumber;
    }

    public StudentViewDto setStudentNumber(Integer studentNumber) {
        this.studentNumber = studentNumber;
        return this;
    }

    public BigDecimal getAverageGrade() {
        return averageGrade;
    }

    public StudentViewDto setAverageGrade(BigDecimal averageGrade) {
        this.averageGrade = averageGrade;
        return this;
    }

    public UserEntity getTeacher() {
        return teacher;
    }

    public StudentViewDto setTeacher(UserEntity teacher) {
        this.teacher = teacher;
        return this;
    }

    public ClassEntity getStudentClass() {
        return studentClass;
    }

    public StudentViewDto setStudentClass(ClassEntity studentClass) {
        this.studentClass = studentClass;
        return this;
    }
}
