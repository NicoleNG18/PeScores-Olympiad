package pmgkn.pescores.pescores.domain.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static pmgkn.pescores.pescores.domain.entity.constants.Messages.OWNER;
import static pmgkn.pescores.pescores.domain.entity.constants.Messages.TEACHER;
import static pmgkn.pescores.pescores.domain.entity.constants.TableNames.USERS;


@Entity
@Table(name = USERS)
public class UserEntity extends BaseEntity {

    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false, unique = true)
    private String email;
    @ManyToOne
    private SchoolEntity school;
    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = OWNER)
    private List<TaskEntity> tasks;

    @OneToMany(mappedBy = TEACHER)
    private List<ClassEntity> classes;

    @OneToMany(mappedBy = TEACHER)
    private List<StudentEntity> students;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<UserRoleEntity> roles = new ArrayList<>();

    public List<UserRoleEntity> getRoles() {
        return roles;
    }

    public UserEntity setRoles(List<UserRoleEntity> roles) {
        this.roles = roles;
        return this;
    }

    public UserEntity() {
        this.tasks = new ArrayList<>();
        this.classes = new ArrayList<>();
        this.students = new ArrayList<>();
    }

    public List<StudentEntity> getStudents() {
        return students;
    }

    public UserEntity setStudents(List<StudentEntity> students) {
        this.students = students;
        return this;
    }

    public List<TaskEntity> getTasks() {
        return tasks;
    }

    public UserEntity setTasks(List<TaskEntity> tasks) {
        this.tasks = tasks;
        return this;
    }

    public List<ClassEntity> getClasses() {
        return classes;
    }

    public UserEntity setClasses(List<ClassEntity> classes) {
        this.classes = classes;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserEntity setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserEntity setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    public SchoolEntity getSchool() {
        return school;
    }

    public UserEntity setSchool(SchoolEntity school) {
        this.school = school;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserEntity setPassword(String password) {
        this.password = password;
        return this;
    }

    public boolean isSchoolAdmin() {
        List<UserRoleEntity> roles = this.getRoles().stream().filter(r -> r.getRole().name().equals("ADMIN")).toList();
        return roles.size() == 1;
    }
}

