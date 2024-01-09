package pmgkn.pescores.pescores.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pmgkn.pescores.pescores.domain.dto.TaskDto;
import pmgkn.pescores.pescores.domain.entity.TaskEntity;
import pmgkn.pescores.pescores.domain.entity.UserEntity;
import pmgkn.pescores.pescores.domain.enums.TaskStatusEnum;
import pmgkn.pescores.pescores.repositories.TaskRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final UserService userService;

    private  final TaskRepository taskRepository;

    private final ModelMapper modelMapper;

    public TaskService(UserService userService,
                       TaskRepository taskRepository,
                       ModelMapper modelMapper) {
        this.userService = userService;
        this.taskRepository = taskRepository;
        this.modelMapper = modelMapper;
    }


    @Transactional
    public void addTask(TaskDto taskDto, String username){

        TaskEntity task = new TaskEntity();

        final UserEntity user = this.userService.getUserByEmail(username);

        task.setDescription(taskDto.getDescription())
                .setOwner(user)
                .setStatus(TaskStatusEnum.IN_PROGRESS);

        user.getTasks().add(task);

        this.taskRepository.saveAndFlush(task);
    }

    public List<TaskDto> getAllTasksByUser(String username){

        List<TaskEntity> allTasks = this.userService.getAllTasks(username);
        return allTasks.stream().map(this::mapToTaskDto).collect(Collectors.toList());
    }

    private TaskDto mapToTaskDto(TaskEntity t) {
        return this.modelMapper.map(t, TaskDto.class);
    }


    public TaskDto getTaskById(UUID id) {

        TaskEntity task= this.taskRepository.findById(id).get();


        return new TaskDto().setDescription(task.getDescription());
    }
}
