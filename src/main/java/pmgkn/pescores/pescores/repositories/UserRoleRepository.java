package pmgkn.pescores.pescores.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pmgkn.pescores.pescores.domain.entity.UserRoleEntity;
import pmgkn.pescores.pescores.domain.enums.UserRoleEnum;

import java.util.UUID;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, UUID> {

    UserRoleEntity findByRole(UserRoleEnum role);
}
