package pmgkn.pescores.pescores.domain.dto.view;

import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.UUID;

import static pmgkn.pescores.pescores.domain.entity.constants.Messages.DATE_PATTERN;

public class TaskViewDto {

    private UUID id;

    private String description;

    @DateTimeFormat(pattern = DATE_PATTERN)
    private LocalDate dueDate;

    public TaskViewDto() {
    }

    public TaskViewDto(UUID id,String description,
                          LocalDate dueDate) {
        this.description=description;
        this.dueDate=dueDate;
        this.id=id;
    }

    public UUID getId() {
        return id;
    }

    public TaskViewDto setId(UUID id) {
        this.id = id;
        return this;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public TaskViewDto setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public TaskViewDto setDescription(String description) {
        this.description = description;
        return this;
    }
}
