package pmgkn.pescores.pescores.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pmgkn.pescores.pescores.domain.entity.*;
import pmgkn.pescores.pescores.domain.enums.TaskStatusEnum;
import pmgkn.pescores.pescores.domain.enums.UserRoleEnum;
import pmgkn.pescores.pescores.repositories.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;

@Component
public class TestDataUtils {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final ClassRepository classRepository;

//    private final StudentRepository studentRepository;

    @Autowired
    public TestDataUtils(TaskRepository taskRepository,
                         UserRepository userRepository,
                         UserRoleRepository userRoleRepository,
                         ClassRepository classRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.classRepository = classRepository;
//        this.studentRepository = studentRepository;
    }

    private void initRoles() {
        if (userRoleRepository.count() == 0) {
            UserRoleEntity adminRole = new UserRoleEntity().setRole(UserRoleEnum.ADMIN);
            UserRoleEntity userRole = new UserRoleEntity().setRole(UserRoleEnum.USER);

            userRoleRepository.save(adminRole);
            userRoleRepository.save(userRole);
        }
    }

//    public StudentEntity createTestStudent(ClassEntity classEntity,UserEntity teacher){
//        StudentEntity testStudent=new StudentEntity()
//                .setStudentClass(classEntity)
//                .setStudentName("Nikol Georgieva")
//                .setStudentNumber(1)
//                .setTeacher(teacher);
//        return studentRepository.saveAndFlush(testStudent);
//    }

    public UserEntity createTestUser(String email) {
        initRoles();

        UserEntity testUser = new UserEntity()
                .setFirstName("test")
                .setLastName("testov")
                .setEmail(email)
                .setSchool("Pmg Prof. Emanuil Ivanov")
                .setPassword("topsecret12@")
                .setTasks(new ArrayList<>())
                .setClasses(new ArrayList<>())
                .setRoles(userRoleRepository.findAll().stream()
                        .filter(r -> r.getRole() == UserRoleEnum.USER)
                        .toList());

        return userRepository.saveAndFlush(testUser);
    }

    public ClassEntity createClass(UserEntity teacher) {

        ClassEntity classEntity = new ClassEntity()
                .setClassName("8A")
                .setClassNum(8)
                .setTeacher(teacher);
        return classRepository.saveAndFlush(classEntity);
    }

    public TaskEntity createTask(UserEntity userEntity) {

        TaskEntity taskEntity = new TaskEntity()
                .setDescription("todo description")
                .setDueDate(LocalDate.of(2017, Month.JANUARY, 25))
                .setOwner(userEntity)
                .setStatus(TaskStatusEnum.IN_PROGRESS);

        return taskRepository.saveAndFlush(taskEntity);
    }

    public void cleanUpDatabase() {
        taskRepository.deleteAll();
        classRepository.deleteAll();
//        studentRepository.deleteAll();
        userRepository.deleteAll();
        userRoleRepository.deleteAll();
    }


}
