package pmgkn.pescores.pescores.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pmgkn.pescores.pescores.domain.entity.TaskEntity;
import pmgkn.pescores.pescores.domain.entity.UserEntity;
import pmgkn.pescores.pescores.domain.entity.UserRoleEntity;
import pmgkn.pescores.pescores.domain.enums.TaskStatusEnum;
import pmgkn.pescores.pescores.domain.enums.UserRoleEnum;
import pmgkn.pescores.pescores.repositories.TaskRepository;
import pmgkn.pescores.pescores.repositories.UserRepository;
import pmgkn.pescores.pescores.repositories.UserRoleRepository;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;

@Component
public class TestDataUtils {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;

    @Autowired
    public TestDataUtils(TaskRepository taskRepository,
                         UserRepository userRepository,
                         UserRoleRepository userRoleRepository){
        this.taskRepository=taskRepository;
        this.userRepository=userRepository;
        this.userRoleRepository=userRoleRepository;
    }

    private void initRoles() {
        if (userRoleRepository.count() == 0) {
            UserRoleEntity adminRole = new UserRoleEntity().setRole(UserRoleEnum.ADMIN);
            UserRoleEntity userRole = new UserRoleEntity().setRole(UserRoleEnum.USER);

            userRoleRepository.save(adminRole);
            userRoleRepository.save(userRole);
        }
    }

    public UserEntity createTestUser(String email){
        initRoles();

        UserEntity testUser=new UserEntity()
                .setFirstName("test")
                .setLastName("testov")
                .setEmail(email)
                .setSchool("Pmg Prof. Emanuil Ivanov")
                .setPassword("topsecret12@")
                .setTasks(new ArrayList<>())
                .setClasses(new ArrayList<>())
                .setRoles(userRoleRepository.findAll().stream()
                        .filter(r->r.getRole()== UserRoleEnum.USER)
                        .toList());

        return userRepository.save(testUser);
    }

    public TaskEntity createTask(UserEntity userEntity){

        TaskEntity taskEntity=new TaskEntity()
                .setDescription("todo description")
                .setDueDate(LocalDate.of(2017, Month.JANUARY,25))
                .setOwner(userEntity)
                .setStatus(TaskStatusEnum.IN_PROGRESS);

        return taskRepository.save(taskEntity);
    }

    public void cleanUpDatabase() {
        taskRepository.deleteAll();
        userRepository.deleteAll();
        userRoleRepository.deleteAll();
    }




}
