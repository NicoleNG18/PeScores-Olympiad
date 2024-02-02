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

    @Column(nullable = true)
    private BigDecimal denseBall;
    @Column(nullable = true)
    private BigDecimal jump;
    @Column(nullable = true)
    private BigDecimal tTest;
    @Column(nullable = true)
    private BigDecimal thirtyMeters;
    @Column(nullable = true)
    private BigDecimal twoHundredMeters;

    //TODO: MAKE THE NORMATIVES BIG DECIMAL
//    @OneToOne
//    private DenseBallEntity denseBall;
//    @OneToOne
//    private TTestEntity tTest;
//    @OneToOne
//    private ThirtyMetersEntity thirtyMeters;
//    @OneToOne
//    private TwoHundredMetersEntity twoHundredMeters;

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

    public BigDecimal getDenseBall() {
        return denseBall;
    }

    public StudentEntity setDenseBall(BigDecimal denseBall) {
        this.denseBall = denseBall;
        return this;
    }

    public BigDecimal getJump() {
        return jump;
    }

    public StudentEntity setJump(BigDecimal jump) {
        this.jump = jump;
        return this;
    }

    public BigDecimal gettTest() {
        return tTest;
    }

    public StudentEntity settTest(BigDecimal tTest) {
        this.tTest = tTest;
        return this;
    }

    public BigDecimal getThirtyMeters() {
        return thirtyMeters;
    }

    public StudentEntity setThirtyMeters(BigDecimal thirtyMeters) {
        this.thirtyMeters = thirtyMeters;
        return this;
    }

    public BigDecimal getTwoHundredMeters() {
        return twoHundredMeters;
    }

    public StudentEntity setTwoHundredMeters(BigDecimal twoHundredMeters) {
        this.twoHundredMeters = twoHundredMeters;
        return this;
    }

    //    public DenseBallEntity getDenseBall() {
//        return denseBall;
//    }
//
//    public StudentEntity setDenseBall(DenseBallEntity denseBall) {
//        this.denseBall = denseBall;
//        return this;
//    }
//
//    public TTestEntity gettTest() {
//        return tTest;
//    }
//
//    public StudentEntity settTest(TTestEntity tTest) {
//        this.tTest = tTest;
//        return this;
//    }
//
//    public ThirtyMetersEntity getThirtyMeters() {
//        return thirtyMeters;
//    }
//
//    public StudentEntity setThirtyMeters(ThirtyMetersEntity thirtyMeters) {
//        this.thirtyMeters = thirtyMeters;
//        return this;
//    }

//    public TwoHundredMetersEntity getTwoHundredMeters() {
//        return twoHundredMeters;
//    }
//
//    public StudentEntity setTwoHundredMeters(TwoHundredMetersEntity twoHundredMeters) {
//        this.twoHundredMeters = twoHundredMeters;
//        return this;
//    }

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
        this.denseBall=BigDecimal.ZERO;
        this.thirtyMeters=BigDecimal.ZERO;
        this.twoHundredMeters=BigDecimal.ZERO;
        this.tTest=BigDecimal.ZERO;
        this.jump=BigDecimal.ZERO;
        this.averageGrade = BigDecimal.ZERO;
    }
}

