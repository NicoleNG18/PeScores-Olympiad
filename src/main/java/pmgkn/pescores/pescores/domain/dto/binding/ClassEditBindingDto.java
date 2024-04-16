package pmgkn.pescores.pescores.domain.dto.binding;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import static pmgkn.pescores.pescores.domain.entity.constants.ValidationErrorMessages.CLASS_NAME_REQUIRED;
import static pmgkn.pescores.pescores.domain.entity.constants.ValidationErrorMessages.CLASS_NUM_REQUIRED;

public class ClassEditBindingDto {

//    @NotEmpty(message = CLASS_NAME_REQUIRED)
//    private String className;
//
//    @Positive
//    @NotNull(message = CLASS_NUM_REQUIRED)
//    private int classNum;

    private String teacher;
    public ClassEditBindingDto() {
    }

    public String getTeacher() {
        return teacher;
    }

    public ClassEditBindingDto setTeacher(String teacher) {
        this.teacher = teacher;
        return this;
    }
//
//    public String getClassName() {
//        return className;
//    }
//
//    public ClassEditBindingDto setClassName(String className) {
//        this.className = className;
//        return this;
//    }
//
//    public int getClassNum() {
//        return classNum;
//    }
//
//    public ClassEditBindingDto setClassNum(int classNum) {
//        this.classNum = classNum;
//        return this;
//    }
}
