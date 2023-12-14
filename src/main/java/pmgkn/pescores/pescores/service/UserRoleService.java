package pmgkn.pescores.pescores.service;

import org.springframework.stereotype.Service;
import pmgkn.pescores.pescores.domain.entity.UserRoleEntity;
import pmgkn.pescores.pescores.domain.enums.UserRoleEnum;
import pmgkn.pescores.pescores.repositories.UserRoleRepository;

@Service
public class UserRoleService {

    private final UserRoleRepository userRoleRepository;

    public UserRoleService(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    public UserRoleEntity getRole(UserRoleEnum role){
        return this.userRoleRepository.findByRole(role);
    }
}
