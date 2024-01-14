package pmgkn.pescores.pescores.domain.entity;

import jakarta.persistence.*;
import pmgkn.pescores.pescores.domain.entity.normatives.DenseBallEntity;
import pmgkn.pescores.pescores.domain.entity.normatives.ThirtyMetersEntity;
import pmgkn.pescores.pescores.domain.entity.normatives.TwoHundredMetersEntity;
import pmgkn.pescores.pescores.domain.entity.normatives.TTestEntity;

import static pmgkn.pescores.pescores.domain.entity.constants.TableNames.STUDENTS;

@Entity
@Table(name = STUDENTS)
public class StudentEntity extends BaseEntity {

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @OneToOne
    private DenseBallEntity denseBall;
    @OneToOne
    private TTestEntity tTest;
    @OneToOne
    private ThirtyMetersEntity thirtyMeters;
    @OneToOne
    private TwoHundredMetersEntity twoHundredMeters;

    @ManyToOne
    private ClassEntity studentClass;

    public String getFirstName() {
        return firstName;
    }

    public StudentEntity setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public StudentEntity setLastName(String lastName) {
        this.lastName = lastName;
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

    public StudentEntity() {
    }
}

