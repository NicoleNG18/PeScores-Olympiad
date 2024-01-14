package pmgkn.pescores.pescores.domain.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static pmgkn.pescores.pescores.domain.entity.constants.TableNames.USERS;


@Entity
@Table(name = USERS)
public class UserEntity extends BaseEntity {

    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false,unique = true)
    private String email;
    @Column(nullable = false)
    private String school;
    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "owner")
    private List<TaskEntity> tasks;

    @OneToMany(mappedBy = "teacher")
    private List<ClassEntity> classes;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<UserRoleEntity> roles = new ArrayList<>();
//    private YearEntity year;


    public List<UserRoleEntity> getRoles() {
        return roles;
    }

    public UserEntity setRoles(List<UserRoleEntity> roles) {
        this.roles = roles;
        return this;
    }

    public UserEntity() {
        this.tasks=new ArrayList<>();
        this.classes=new ArrayList<>();
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

    public String getSchool() {
        return school;
    }

    public UserEntity setSchool(String school) {
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
}

