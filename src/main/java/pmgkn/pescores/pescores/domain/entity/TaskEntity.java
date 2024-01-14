package pmgkn.pescores.pescores.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import pmgkn.pescores.pescores.domain.enums.TaskStatusEnum;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static pmgkn.pescores.pescores.domain.entity.constants.TableNames.TASKS;

@Entity
@Table(name = TASKS)
public class TaskEntity extends BaseEntity{

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TaskStatusEnum status;
    @Column(nullable = true)
    private String description;

    @Column(nullable = false,name = "due_date")
    private LocalDate dueDate;

    @ManyToOne
    private UserEntity owner;

    public TaskEntity() {
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public TaskEntity setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
        return this;
    }

    public TaskStatusEnum getStatus() {
        return status;
    }

    public void setStatus(TaskStatusEnum status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public TaskEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public UserEntity getOwner() {
        return owner;
    }

    public TaskEntity setOwner(UserEntity owner) {
        this.owner = owner;
        return this;
    }
}

