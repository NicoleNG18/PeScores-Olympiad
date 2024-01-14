package pmgkn.pescores.pescores.domain.dto;

import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class TaskDto {
    @NotNull
    private String description;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private LocalDate dueDate;

    public TaskDto() {
    }

    public TaskDto(String description,
                   LocalDate dueDate) {
        this.description=description;
        this.dueDate=dueDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public TaskDto setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public TaskDto setDescription(String description) {
        this.description = description;
        return this;
    }
}
