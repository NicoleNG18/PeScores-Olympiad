package pmgkn.pescores.pescores.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pmgkn.pescores.pescores.domain.dto.TaskDto;
import pmgkn.pescores.pescores.domain.entity.TaskEntity;
import pmgkn.pescores.pescores.domain.entity.UserEntity;
import pmgkn.pescores.pescores.domain.enums.TaskStatusEnum;
import pmgkn.pescores.pescores.repositories.TaskRepository;

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
//        List<TaskDto> allTasksDtos = allTasksByUser.stream().map(this::mapToTaskDto).collect(Collectors.toList());

////        allTasksDtos.stream()
////                .sorted(Comparator.comparing(TaskDto::getDueDate))
////                .collect(Collectors.toList());
//
//                Comparator<TaskDto> comparatorAsc = (task1, task2) -> (task1.getDueDate().isBefore(task2.getDueDate()) ? 1 : 0);
//
//        allTasksDtos.sort(comparatorAsc);

        return allTasksByUser.stream().map(this::mapToTaskDto).collect(Collectors.toList());
    }

    public List<TaskDto> getAllDoneTasksByUser(String username){

        List<TaskEntity> allTasksByUser = this.userService.getAllDoneTasks(username);
//        List<TaskDto> allTasksDtos = allTasksByUser.stream().map(this::mapToTaskDto).collect(Collectors.toList());

//        return allTasksByUser.stream().map(this::mapToTaskDto).collect(Collectors.toList());
//        Comparator<TaskDto> comparatorAsc = (task1, task2) -> task1.getDueDate()
//                .compareTo(task2.getDueDate());
//
//        allTasksDtos.sort(comparatorAsc);
//
//        allTasksDtos.stream()
//                .sorted(Comparator.comparing(TaskDto::getDueDate))
//                .collect(Collectors.toList());

        return allTasksByUser.stream().map(this::mapToTaskDto).collect(Collectors.toList());
    }

    private TaskDto mapToTaskDto(TaskEntity t) {
        return this.modelMapper.map(t, TaskDto.class);
    }

}
