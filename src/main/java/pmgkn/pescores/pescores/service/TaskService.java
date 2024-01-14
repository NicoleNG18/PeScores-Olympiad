package pmgkn.pescores.pescores.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pmgkn.pescores.pescores.domain.dto.TaskDto;
import pmgkn.pescores.pescores.domain.entity.TaskEntity;
import pmgkn.pescores.pescores.domain.entity.UserEntity;
import pmgkn.pescores.pescores.domain.enums.TaskStatusEnum;
import pmgkn.pescores.pescores.repositories.TaskRepository;

import java.security.Principal;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
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
    public void saveTask(TaskDto taskDto, String username){

        TaskEntity task = new TaskEntity();

        final UserEntity user = this.userService.getUserByEmail(username);

        buildTask(taskDto, task, user);

        user.getTasks().add(task);

        this.taskRepository.saveAndFlush(task);
    }

    private static void buildTask(TaskDto taskDto,
                                  TaskEntity task,
                                  UserEntity user) {
        task.setDescription(taskDto.getDescription())
                .setOwner(user)
                .setDueDate(taskDto.getDueDate())
                .setStatus(TaskStatusEnum.IN_PROGRESS);
    }

    public List<TaskDto> getAllInProgressTasksByUser(String username){

        List<TaskEntity> allTasksByUser = this.userService.getAllInProgressTasks(username);

        Comparator<TaskEntity> compareByDate= Comparator.comparing(TaskEntity::getDueDate);
        allTasksByUser.sort(compareByDate);

        return allTasksByUser.stream().map(this::mapToTaskDto).collect(Collectors.toList());
    }

    public List<TaskDto> getAllDoneTasksByUser(String username){

        List<TaskEntity> allTasksByUser = this.userService.getAllDoneTasks(username);

        Comparator<TaskEntity> compareByDate= Comparator.comparing(TaskEntity::getDueDate);
        allTasksByUser.sort(compareByDate);


        return allTasksByUser.stream().map(this::mapToTaskDto).collect(Collectors.toList());
    }

    private TaskDto mapToTaskDto(TaskEntity t) {
        return this.modelMapper.map(t, TaskDto.class);
    }

    public void makeTaskDone(String descr,String email) {

        UserEntity userByEmail = this.userService.getUserByEmail(email);

        TaskEntity byOwnerAndDescription = this.taskRepository.findByOwnerAndDescription(userByEmail, descr);
        byOwnerAndDescription.setStatus(TaskStatusEnum.COMPLETED);

        this.taskRepository.saveAndFlush(byOwnerAndDescription);

    }

    public void deleteTask(String descr, String email){

        UserEntity userByEmail = this.userService.getUserByEmail(email);
        TaskEntity byOwnerAndDescription = this.taskRepository.findByOwnerAndDescription(userByEmail, descr);
        this.taskRepository.delete(byOwnerAndDescription);

    }
}
