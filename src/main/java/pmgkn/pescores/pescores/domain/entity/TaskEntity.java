package pmgkn.pescores.pescores.domain.entity;

import jakarta.persistence.*;
import pmgkn.pescores.pescores.domain.enums.TaskStatusEnum;

import java.time.LocalDate;

import static pmgkn.pescores.pescores.domain.entity.constants.Messages.DUE_DATE;
import static pmgkn.pescores.pescores.domain.entity.constants.TableNames.TASKS;

@Entity
@Table(name = TASKS)
public class TaskEntity extends BaseEntity {

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TaskStatusEnum status;
    @Column
    private String description;

    @Column(nullable = false, name = DUE_DATE)
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

    public TaskEntity setStatus(TaskStatusEnum status) {
        this.status = status;
        return this;
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

