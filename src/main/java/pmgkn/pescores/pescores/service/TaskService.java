package pmgkn.pescores.pescores.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pmgkn.pescores.pescores.domain.dto.TaskDto;
import pmgkn.pescores.pescores.domain.entity.TaskEntity;
import pmgkn.pescores.pescores.domain.entity.UserEntity;
import pmgkn.pescores.pescores.domain.enums.TaskStatusEnum;
import pmgkn.pescores.pescores.repositories.TaskRepository;

import java.util.UUID;

@Service
public class TaskService {

    private final UserService userService;

    private  final TaskRepository taskRepository;

    public TaskService(UserService userService,
                       TaskRepository taskRepository) {
        this.userService = userService;
        this.taskRepository = taskRepository;
    }


    @Transactional
    public UUID addTask(TaskDto taskDto, String username){

        TaskEntity task = new TaskEntity();

        final UserEntity user = this.userService.getUserByEmail(username);

        task.setDescription(taskDto.getDescription())
                .setOwner(user)
                .setStatus(TaskStatusEnum.IN_PROGRESS);

        user.getTasks().add(task);

        this.taskRepository.saveAndFlush(task);

        return task.getId();

    }


    public TaskDto getTaskById(UUID id) {

        TaskEntity task= this.taskRepository.findById(id).get();


        return new TaskDto().setDescription(task.getDescription());
    }
}
