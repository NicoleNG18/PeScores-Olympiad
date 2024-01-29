package pmgkn.pescores.pescores.domain.dto.view;

import pmgkn.pescores.pescores.domain.entity.StudentEntity;

import java.util.List;
import java.util.UUID;

public class ClassViewDto {

    private UUID id;

    private  String className;

    private int classNum;

    private List<StudentEntity> students;

    public ClassViewDto() {
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
