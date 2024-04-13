package pmgkn.pescores.pescores.domain.dto.view;

import pmgkn.pescores.pescores.domain.entity.ClassEntity;
import pmgkn.pescores.pescores.domain.entity.StudentEntity;
import pmgkn.pescores.pescores.domain.entity.UserEntity;

import java.util.List;
import java.util.UUID;

public class SchoolViewDto {

    private UUID id;

    private String schoolName;

    private String city;

    private List<ClassEntity> classes;

    private List<StudentEntity> students;

    private List<UserEntity> teachers;

    public SchoolViewDto() {
    }

    public UUID getId() {
        return id;
    }

    public SchoolViewDto setId(UUID id) {
        this.id = id;
        return this;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public SchoolViewDto setSchoolName(String schoolName) {
        this.schoolName = schoolName;
        return this;
    }

    public String getCity() {
        return city;
    }

    public SchoolViewDto setCity(String city) {
        this.city = city;
        return this;
    }

    public List<ClassEntity> getClasses() {
        return classes;
    }

    public SchoolViewDto setClasses(List<ClassEntity> classes) {
        this.classes = classes;
        return this;
    }

    public List<StudentEntity> getStudents() {
        return students;
    }

    public SchoolViewDto setStudents(List<StudentEntity> students) {
        this.students = students;
        return this;
    }

    public List<UserEntity> getTeachers() {
        return teachers;
    }

    public SchoolViewDto setTeachers(List<UserEntity> teachers) {
        this.teachers = teachers;
        return this;
    }
}
