package pmgkn.pescores.pescores.domain.dto.binding;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import pmgkn.pescores.pescores.validation.common.ValidTaskName;

import java.time.LocalDate;

import static pmgkn.pescores.pescores.domain.entity.constants.Messages.DATE_PATTERN;
import static pmgkn.pescores.pescores.domain.entity.constants.ValidationErrorMessages.*;

public class TaskAddBindingDto {

    @ValidTaskName
    private String description;

    @DateTimeFormat(pattern = DATE_PATTERN)
    @FutureOrPresent(message = DATE_PRESENT)
    @NotNull(message = DATE_PRESENT)
    private LocalDate dueDate;

    public TaskAddBindingDto() {
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public TaskAddBindingDto setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public TaskAddBindingDto setDescription(String description) {
        this.description = description;
        return this;
    }
}
