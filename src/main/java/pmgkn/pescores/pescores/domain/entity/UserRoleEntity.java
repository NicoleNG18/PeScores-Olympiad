package pmgkn.pescores.pescores.domain.entity;


import jakarta.persistence.*;
import pmgkn.pescores.pescores.domain.enums.UserRoleEnum;

import static pmgkn.pescores.pescores.domain.entity.constants.TableNames.ROLES;

@Entity
@Table(name = ROLES)
public class UserRoleEntity extends BaseEntity {

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRoleEnum role;

    public UserRoleEntity() {
    }

    public UserRoleEnum getRole() {
        return role;
    }

    public UserRoleEntity setRole(UserRoleEnum role) {
        this.role = role;
        return this;
    }

}

