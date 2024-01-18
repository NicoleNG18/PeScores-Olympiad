package pmgkn.pescores.pescores.domain.dto.binding;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

import static pmgkn.pescores.pescores.domain.entity.constants.Messages.DATE_PATTERN;
import static pmgkn.pescores.pescores.domain.entity.constants.ValidationErrorMessages.*;

public class TaskBindingDto {

    @NotEmpty(message = DESCRIPTION_REQUIRED)
    private String description;

    @DateTimeFormat(pattern = DATE_PATTERN)
    @FutureOrPresent(message = DATE_PRESENT)
    @NotNull(message = DATE_PRESENT)
    private LocalDate dueDate;

    public TaskBindingDto() {
    }

    public TaskBindingDto(String description,
                          LocalDate dueDate) {
        this.description=description;
        this.dueDate=dueDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public TaskBindingDto setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public TaskBindingDto setDescription(String description) {
        this.description = description;
        return this;
    }
}
