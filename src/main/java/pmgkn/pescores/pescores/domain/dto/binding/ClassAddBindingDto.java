package pmgkn.pescores.pescores.domain.dto.binding;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import pmgkn.pescores.pescores.validation.common.ValidClassName;

import static pmgkn.pescores.pescores.domain.entity.constants.ValidationErrorMessages.CLASS_NAME_REQUIRED;
import static pmgkn.pescores.pescores.domain.entity.constants.ValidationErrorMessages.CLASS_NUM_REQUIRED;

public class ClassAddBindingDto {

    @NotEmpty(message = CLASS_NAME_REQUIRED)
    @ValidClassName
    private String className;

    @Positive
    @NotNull(message = CLASS_NUM_REQUIRED)
    private int classNum;

    public ClassAddBindingDto() {
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
