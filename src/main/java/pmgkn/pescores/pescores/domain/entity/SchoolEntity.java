package pmgkn.pescores.pescores.domain.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Table(name = "schools")
@Entity
public class SchoolEntity extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String schoolName;

    @Column(nullable = false)
    private String city;

    @OneToMany(cascade = CascadeType.ALL)
    private List<ClassEntity> classes;

    @OneToMany(cascade = CascadeType.ALL)
    private List<StudentEntity> students;

    @OneToMany(cascade = CascadeType.ALL)
    private List<UserEntity> teachers;

    public SchoolEntity() {
        this.classes = new ArrayList<>();
        this.teachers = new ArrayList<>();
        this.students = new ArrayList<>();
    }

    public String getName() {
        return schoolName;
    }

    public SchoolEntity setName(String schoolName) {
        this.schoolName = schoolName;
        return this;
    }

    public String getCity() {
        return city;
    }

    public SchoolEntity setCity(String city) {
        this.city = city;
        return this;
    }

    public List<ClassEntity> getClasses() {
        return classes;
    }

    public SchoolEntity setClasses(List<ClassEntity> classes) {
        this.classes = classes;
        return this;
    }

    public List<StudentEntity> getStudents() {
        return students;
    }

    public SchoolEntity setStudents(List<StudentEntity> students) {
        this.students = students;
        return this;
    }

    public void addTeacher(UserEntity teacher) {
        this.teachers.add(teacher);
    }

    public List<UserEntity> getTeachers() {
        return teachers;
    }

    public SchoolEntity setTeachers(List<UserEntity> teachers) {
        this.teachers = teachers;
        return this;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public SchoolEntity setSchoolName(String schoolName) {
        this.schoolName = schoolName;
        return this;
    }
}
