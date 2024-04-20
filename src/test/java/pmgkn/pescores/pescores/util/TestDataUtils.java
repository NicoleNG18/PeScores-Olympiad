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

    private final SchoolRepository schoolRepository;

    private SchoolEntity school;

    @Autowired
    public TestDataUtils(TaskRepository taskRepository,
                         UserRepository userRepository,
                         UserRoleRepository userRoleRepository,
                         ClassRepository classRepository,
                         SchoolRepository schoolRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.classRepository = classRepository;
        this.schoolRepository = schoolRepository;
    }

    private void initRoles() {
        if (userRoleRepository.count() == 0) {
            UserRoleEntity adminRole = new UserRoleEntity().setRole(UserRoleEnum.ADMIN);
            UserRoleEntity userRole = new UserRoleEntity().setRole(UserRoleEnum.USER);
            UserRoleEntity superadminRole = new UserRoleEntity().setRole(UserRoleEnum.SUPERADMIN);

            userRoleRepository.save(adminRole);
            userRoleRepository.save(userRole);
            userRoleRepository.save(superadminRole);
        }
    }

    public SchoolEntity createSchool() {

        this.school = new SchoolEntity()
                .setSchoolName("PMG Prof. Emanuil Ivanov")
                .setCity("Kyustendil")
                .setClasses(new ArrayList<>())
                .setTeachers(new ArrayList<>())
                .setStudents(new ArrayList<>());


         return schoolRepository.saveAndFlush(school);
    }

    public UserEntity createTestUser(String email) {
        initRoles();

        UserEntity testUser = new UserEntity()
                .setFirstName("user")
                .setLastName("userov")
                .setEmail(email)
                .setSchool(this.school)
                .setPassword("topsecret12@")
                .setTasks(new ArrayList<>())
                .setClasses(new ArrayList<>())
                .setStudents(new ArrayList<>())
                .setRoles(userRoleRepository.findAll().stream()
                        .filter(r -> r.getRole() == UserRoleEnum.USER)
                        .toList());

        return userRepository.saveAndFlush(testUser);
    }

    public UserEntity createTestAdmin(String email) {
        initRoles();

        UserEntity testUser = new UserEntity()
                .setFirstName("admin")
                .setLastName("adminov")
                .setEmail(email)
                .setSchool(this.school)
                .setPassword("topsecret12@")
                .setTasks(new ArrayList<>())
                .setClasses(new ArrayList<>())
                .setStudents(new ArrayList<>())
                .setRoles(userRoleRepository.findAll().stream()
                        .filter(r -> r.getRole() != UserRoleEnum.SUPERADMIN)
                        .toList());

        return userRepository.saveAndFlush(testUser);
    }

    public UserEntity createTestSuperAdmin(String email) {
        initRoles();

        UserEntity testUser = new UserEntity()
                .setFirstName("super")
                .setLastName("admin")
                .setEmail(email)
                .setSchool(this.school)
                .setPassword("topsecret12@")
                .setTasks(new ArrayList<>())
                .setClasses(new ArrayList<>())
                .setStudents(new ArrayList<>())
                .setRoles(userRoleRepository.findAll());

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
        userRepository.deleteAll();
        userRoleRepository.deleteAll();
        schoolRepository.deleteAll();
    }


}
