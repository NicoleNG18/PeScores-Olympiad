package pmgkn.pescores.pescores.domain.entity;

import jakarta.persistence.*;
import pmgkn.pescores.pescores.domain.enums.TaskStatusEnum;

@Entity
@Table(name = "tasks")
public class TaskEntity extends BaseEntity{

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TaskStatusEnum status;
    @Column(nullable = true)
    private String description;

//    private Date dueDate;

    @ManyToOne
    private UserEntity owner;

    public TaskEntity() {
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

