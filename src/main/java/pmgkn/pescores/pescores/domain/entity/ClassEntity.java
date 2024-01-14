package pmgkn.pescores.pescores.domain.entity;

import jakarta.persistence.*;

import java.util.List;

import static pmgkn.pescores.pescores.domain.entity.constants.TableNames.CLASSES;

@Entity
@Table(name = CLASSES)
public class ClassEntity extends BaseEntity {

    @Column(nullable = false)
    private  String className;

    @Column(nullable = false)
    private int classNum;

    @OneToMany
    private List<StudentEntity> students;

    @ManyToOne
    private UserEntity teacher;

    public ClassEntity() {

    }

    public String getClassName() {
        return className;
    }

    public ClassEntity setClassName(String className) {
        this.className = className;
        return this;
    }

    public int getClassNum() {
        return classNum;
    }

    public ClassEntity setClassNum(int classNum) {
        this.classNum = classNum;
        return this;
    }

    public List<StudentEntity> getStudents() {
        return students;
    }

    public ClassEntity setStudents(List<StudentEntity> students) {
        this.students = students;
        return this;
    }

    public UserEntity getTeacher() {
        return teacher;
    }

    public ClassEntity setTeacher(UserEntity teacher) {
        this.teacher = teacher;
        return this;
    }
}

