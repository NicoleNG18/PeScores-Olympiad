package pmgkn.pescores.pescores.domain.entity;

import jakarta.persistence.*;
import pmgkn.pescores.pescores.domain.entity.normatives.DenseBallEntity;
import pmgkn.pescores.pescores.domain.entity.normatives.ThirtyMetersEntity;
import pmgkn.pescores.pescores.domain.entity.normatives.TwoHundredMetersEntity;
import pmgkn.pescores.pescores.domain.entity.normatives.TTestEntity;
import pmgkn.pescores.pescores.domain.enums.GenderEnum;

import java.math.BigDecimal;

import static pmgkn.pescores.pescores.domain.entity.constants.TableNames.STUDENTS;

@Entity
@Table(name = STUDENTS)
public class StudentEntity extends BaseEntity {

    @Column(nullable = false)
    private String studentName;

    @Column(nullable = false)
    private Integer studentNumber;

    @OneToOne
    private DenseBallEntity denseBall;
    @OneToOne
    private TTestEntity tTest;
    @OneToOne
    private ThirtyMetersEntity thirtyMeters;
    @OneToOne
    private TwoHundredMetersEntity twoHundredMeters;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private GenderEnum gender;

    @Column
    private BigDecimal averageGrade;

    @ManyToOne
    private UserEntity teacher;

    @ManyToOne
    private ClassEntity studentClass;

    public GenderEnum getGender() {
        return gender;
    }

    public StudentEntity setGender(GenderEnum gender) {
        this.gender = gender;
        return this;
    }

    public BigDecimal getAverageGrade() {
        return averageGrade;
    }

    public StudentEntity setAverageGrade(BigDecimal averageGrade) {
        this.averageGrade = averageGrade;
        return this;
    }

    public String getStudentName() {
        return studentName;
    }

    public StudentEntity setStudentName(String studentName) {
        this.studentName = studentName;
        return this;
    }

    public Integer getStudentNumber() {
        return studentNumber;
    }

    public StudentEntity setStudentNumber(Integer studentNumber) {
        this.studentNumber = studentNumber;
        return this;
    }

    public DenseBallEntity getDenseBall() {
        return denseBall;
    }

    public StudentEntity setDenseBall(DenseBallEntity denseBall) {
        this.denseBall = denseBall;
        return this;
    }

    public TTestEntity gettTest() {
        return tTest;
    }

    public StudentEntity settTest(TTestEntity tTest) {
        this.tTest = tTest;
        return this;
    }

    public ThirtyMetersEntity getThirtyMeters() {
        return thirtyMeters;
    }

    public StudentEntity setThirtyMeters(ThirtyMetersEntity thirtyMeters) {
        this.thirtyMeters = thirtyMeters;
        return this;
    }

    public TwoHundredMetersEntity getTwoHundredMeters() {
        return twoHundredMeters;
    }

    public StudentEntity setTwoHundredMeters(TwoHundredMetersEntity twoHundredMeters) {
        this.twoHundredMeters = twoHundredMeters;
        return this;
    }

    public ClassEntity getStudentClass() {
        return studentClass;
    }

    public StudentEntity setStudentClass(ClassEntity studentClass) {
        this.studentClass = studentClass;
        return this;
    }

    public UserEntity getTeacher() {
        return teacher;
    }

    public StudentEntity setTeacher(UserEntity teacher) {
        this.teacher = teacher;
        return this;
    }

    public StudentEntity() {
        this.averageGrade=BigDecimal.ZERO;
    }
}

