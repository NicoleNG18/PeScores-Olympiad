package pmgkn.pescores.pescores.domain.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity{

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

    @ManyToOne
    private UserRoleEntity role;
//    private YearEntity year;


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

    public UserRoleEntity getRole() {
        return role;
    }

    public UserEntity setRole(UserRoleEntity role) {
        this.role = role;
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

