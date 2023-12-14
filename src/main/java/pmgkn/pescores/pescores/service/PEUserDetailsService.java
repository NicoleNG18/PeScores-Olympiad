package pmgkn.pescores.pescores.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pmgkn.pescores.pescores.domain.entity.UserEntity;
import pmgkn.pescores.pescores.domain.entity.UserRoleEntity;
import pmgkn.pescores.pescores.repositories.UserRepository;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PEUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public PEUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        return new User(user.getEmail(), user.getPassword(),mapRolesToAuthorities(Collections.singletonList(user.getRole())));
    }

    private Collection<GrantedAuthority> mapRolesToAuthorities(List<UserRoleEntity> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRole().name())).collect(Collectors.toList());
    }
}


