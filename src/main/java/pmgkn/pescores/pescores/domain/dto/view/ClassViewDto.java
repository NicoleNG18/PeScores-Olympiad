package pmgkn.pescores.pescores.domain.dto.view;

import pmgkn.pescores.pescores.domain.entity.StudentEntity;
import pmgkn.pescores.pescores.domain.entity.UserEntity;

import java.util.List;
import java.util.UUID;

public class ClassViewDto {

    private UUID id;

    private  String className;

    private int classNum;

    private List<StudentEntity> students;

    private UserEntity teacher;

    public ClassViewDto() {
    }

    public UserEntity getTeacher() {
        return teacher;
    }

    public ClassViewDto setTeacher(UserEntity teacher) {
        this.teacher = teacher;
        return this;
    }

    public UUID getId() {
        return id;
    }

    public ClassViewDto setId(UUID id) {
        this.id = id;
        return this;
    }

    public String getClassName() {
        return className;
    }

    public ClassViewDto setClassName(String className) {
        this.className = className;
        return this;
    }

    public int getClassNum() {
        return classNum;
    }

    public ClassViewDto setClassNum(int classNum) {
        this.classNum = classNum;
        return this;
    }

    public List<StudentEntity> getStudents() {
        return students;
    }

    public ClassViewDto setStudents(List<StudentEntity> students) {
        this.students = students;
        return this;
    }
}
