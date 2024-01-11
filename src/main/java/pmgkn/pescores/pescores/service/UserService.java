package pmgkn.pescores.pescores.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pmgkn.pescores.pescores.domain.dto.UserRegistrationDTO;
import pmgkn.pescores.pescores.domain.entity.TaskEntity;
import pmgkn.pescores.pescores.domain.entity.UserEntity;
import pmgkn.pescores.pescores.domain.entity.UserRoleEntity;
import pmgkn.pescores.pescores.domain.enums.TaskStatusEnum;
import pmgkn.pescores.pescores.domain.enums.UserRoleEnum;
import pmgkn.pescores.pescores.repositories.UserRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final ModelMapper modelMapper;
    private final UserRoleService userRoleService;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;


    @Autowired
    public UserService(UserRoleService userRoleService,
                       ModelMapper modelMapper,
                       PasswordEncoder passwordEncoder,
                       UserRepository userRepository) {
        this.userRoleService = userRoleService;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }


    public UserEntity getUserByEmail(String username) {
        return this.userRepository.findByEmail(username);
    }

    public void registerUser(UserRegistrationDTO userRegistrationDTO) {

        UserEntity userToSave = this.mapToUserEntity(userRegistrationDTO);

        this.userRepository.saveAndFlush(userToSave);

    }

    public List<TaskEntity> getAllInProgressTasks(String email) {

        return this.userRepository.findByEmail(email)
                .getTasks().stream().filter(t-> t.getStatus()==TaskStatusEnum.IN_PROGRESS).collect(Collectors.toList());

    }

    public List<TaskEntity> getAllDoneTasks(String email) {

        return this.userRepository.findByEmail(email)
                .getTasks().stream().filter(t-> t.getStatus()==TaskStatusEnum.COMPLETED).collect(Collectors.toList());

    }

    public UserEntity mapToUserEntity(UserRegistrationDTO userRegistrationDTO) {
        final UserRoleEntity userRole = this.userRoleService.getRole(UserRoleEnum.USER);
        return this.modelMapper.map(userRegistrationDTO, UserEntity.class)
                .setRoles(new ArrayList<>(Collections.singletonList(userRole)))
                .setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword()));
    }
}
