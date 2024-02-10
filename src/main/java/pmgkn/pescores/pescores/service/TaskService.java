package pmgkn.pescores.pescores.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pmgkn.pescores.pescores.domain.dto.binding.TaskAddBindingDto;
import pmgkn.pescores.pescores.domain.dto.view.TaskViewDto;
import pmgkn.pescores.pescores.domain.entity.TaskEntity;
import pmgkn.pescores.pescores.domain.entity.UserEntity;
import pmgkn.pescores.pescores.domain.enums.TaskStatusEnum;
import pmgkn.pescores.pescores.repositories.TaskRepository;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final UserService userService;

    private final TaskRepository taskRepository;

    private final ModelMapper modelMapper;

    public TaskService(UserService userService,
                       TaskRepository taskRepository,
                       ModelMapper modelMapper) {
        this.userService = userService;
        this.taskRepository = taskRepository;
        this.modelMapper = modelMapper;
    }


    @Transactional
    public void saveTask(TaskAddBindingDto taskDto,
                         String username) {

        TaskEntity task = new TaskEntity();

        final UserEntity user = this.userService.getUserByEmail(username);

        buildTask(taskDto, task, user);

        user.getTasks().add(task);

        this.taskRepository.saveAndFlush(task);
    }

    private static void buildTask(TaskAddBindingDto taskDto,
                                  TaskEntity task,
                                  UserEntity user) {
        task.setDescription(taskDto.getDescription())
                .setOwner(user)
                .setDueDate(taskDto.getDueDate())
                .setStatus(TaskStatusEnum.IN_PROGRESS);
    }

    public List<TaskViewDto> getAllInProgressTasksByUser(String username) {

        List<TaskEntity> allInProgressTasks = getSortedTasks(username, TaskStatusEnum.IN_PROGRESS);

        return allInProgressTasks.stream().map(this::mapToTaskDto).collect(Collectors.toList());
    }

    public List<TaskViewDto> getAllDoneTasksByUser(String username) {

        List<TaskEntity> allDoneTasks = getSortedTasks(username, TaskStatusEnum.COMPLETED);

        return allDoneTasks.stream().map(this::mapToTaskDto).collect(Collectors.toList());
    }

    private List<TaskEntity> getSortedTasks(String username,
                                            TaskStatusEnum taskStatusEnum) {
        List<TaskEntity> allInProgressTasks = taskStatusEnum.equals(TaskStatusEnum.IN_PROGRESS) ? this.userService.getAllInProgressTasks(username)
                : this.userService.getAllDoneTasks(username);
        sortTasks(allInProgressTasks);

        return allInProgressTasks;
    }

    private static void sortTasks(List<TaskEntity> allInProgressTasks) {
        Comparator<TaskEntity> compareByDate = Comparator.comparing(TaskEntity::getDueDate);
        allInProgressTasks.sort(compareByDate);
    }

    private TaskViewDto mapToTaskDto(TaskEntity t) {
        return this.modelMapper.map(t, TaskViewDto.class);
    }

    public void makeTaskDone(UUID descr) {

        TaskEntity taskById = this.taskRepository.getReferenceById(descr);
        taskById.setStatus(TaskStatusEnum.COMPLETED);

        this.taskRepository.saveAndFlush(taskById);

    }

    public void makeTaskDeleted(UUID descr) {

        TaskEntity taskById = this.taskRepository.getReferenceById(descr);
        taskById.setStatus(TaskStatusEnum.DELETED);

        this.taskRepository.saveAndFlush(taskById);

    }

}
