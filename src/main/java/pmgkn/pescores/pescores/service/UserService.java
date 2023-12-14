package pmgkn.pescores.pescores.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pmgkn.pescores.pescores.domain.dto.UserRegistrationDTO;
import pmgkn.pescores.pescores.domain.entity.UserEntity;
import pmgkn.pescores.pescores.domain.entity.UserRoleEntity;
import pmgkn.pescores.pescores.domain.enums.UserRoleEnum;
import pmgkn.pescores.pescores.repositories.UserRepository;

@Service
public class UserService {

    private final PEUserDetailsService peUserDetailsService;
    private final ModelMapper modelMapper;
    private final UserRoleService userRoleService;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;



    @Autowired
    public UserService(PEUserDetailsService peUserDetailsService,
                       UserRoleService userRoleService,
                       ModelMapper modelMapper,
                       PasswordEncoder passwordEncoder,
                       UserRepository userRepository) {
        this.peUserDetailsService = peUserDetailsService;
        this.userRoleService = userRoleService;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.userRepository=userRepository;
    }

    public void registerUser(UserRegistrationDTO userRegistrationDTO) {

        UserEntity userToSave = this.mapToUserEntity(userRegistrationDTO);

        this.userRepository.saveAndFlush(userToSave);

    }


    public void createUserIfNotExist(String email, String names) {
        // Create manually a user in the database
        // password not necessary
    }

    public UserEntity mapToUserEntity(UserRegistrationDTO userRegistrationDTO) {
        final UserRoleEntity userRole = this.userRoleService.getRole(UserRoleEnum.USER);
        return this.modelMapper.map(userRegistrationDTO,UserEntity.class)
                .setRole(userRole)
                .setPassword(passwordEncoder.encode(userRegistrationDTO.password()));
    }

    public Authentication login(String email) {
        UserDetails userDetails = peUserDetailsService.loadUserByUsername(email);

        Authentication auth = new UsernamePasswordAuthenticationToken(
                userDetails,
                userDetails.getPassword(),
                userDetails.getAuthorities()
        );

        SecurityContextHolder.getContext().setAuthentication(auth);

        return auth;
    }
}
