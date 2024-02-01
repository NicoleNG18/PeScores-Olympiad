package pmgkn.pescores.pescores.domain.dto.view;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import pmgkn.pescores.pescores.domain.entity.ClassEntity;
import pmgkn.pescores.pescores.domain.entity.UserEntity;
import pmgkn.pescores.pescores.domain.enums.GenderEnum;


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

    private GenderEnum gender;

    public StudentViewDto() {
    }

    public BigDecimal getThirtyMeters() {
        return thirtyMeters;
    }

    public StudentViewDto setThirtyMeters(BigDecimal thirtyMeters) {
        this.thirtyMeters = thirtyMeters;
        return this;
    }

    public BigDecimal getTwoHundredMeters() {
        return twoHundredMeters;
    }

    public StudentViewDto setTwoHundredMeters(BigDecimal twoHundredMeters) {
        this.twoHundredMeters = twoHundredMeters;
        return this;
    }

    public BigDecimal gettTest() {
        return tTest;
    }

    public StudentViewDto settTest(BigDecimal tTest) {
        this.tTest = tTest;
        return this;
    }

    public BigDecimal getJump() {
        return jump;
    }

    public StudentViewDto setJump(BigDecimal jump) {
        this.jump = jump;
        return this;
    }

    public GenderEnum getGender() {
        return gender;
    }

    public StudentViewDto setGender(GenderEnum gender) {
        this.gender = gender;
        return this;
    }

    public BigDecimal getDenseBall() {
        return denseBall;
    }

    public StudentViewDto setDenseBall(BigDecimal denseBall) {
        this.denseBall = denseBall;
        return this;
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
