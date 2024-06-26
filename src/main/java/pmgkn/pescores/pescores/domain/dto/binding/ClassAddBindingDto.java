package pmgkn.pescores.pescores.domain.dto.binding;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import pmgkn.pescores.pescores.validation.common.ValidClassName;

import static pmgkn.pescores.pescores.domain.entity.constants.ValidationErrorMessages.CLASS_NUM_REQUIRED;

public class ClassAddBindingDto {
    @ValidClassName
    private String className;

    @Positive
    @NotNull(message = CLASS_NUM_REQUIRED)
    private int classNum;

    private String teacher;

    public ClassAddBindingDto() {
    }

    public String getTeacher() {
        return teacher;
    }

    public ClassAddBindingDto setTeacher(String teacher) {
        this.teacher = teacher;
        return this;
    }

    public String getClassName() {
        return className;
    }

    public ClassAddBindingDto setClassName(String className) {
        this.className = className;
        return this;
    }

    public int getClassNum() {
        return classNum;
    }

    public ClassAddBindingDto setClassNum(int classNum) {
        this.classNum = classNum;
        return this;
    }
}
