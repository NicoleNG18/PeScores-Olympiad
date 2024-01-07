package pmgkn.pescores.pescores.domain.dto;

public class TaskDto {

    private String description;

    public TaskDto() {
    }

    public TaskDto(String description) {
        this.description=description;
    }

    public String getDescription() {
        return description;
    }

    public TaskDto setDescription(String description) {
        this.description = description;
        return this;
    }
}
