package pmgkn.pescores.pescores.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.quartz.QuartzTransactionManager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pmgkn.pescores.pescores.domain.entity.UserEntity;
import pmgkn.pescores.pescores.domain.entity.UserRoleEntity;
import pmgkn.pescores.pescores.repositories.UserRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PEUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public PEUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<UserEntity> byEmail = userRepository.findByEmail(email);
        return byEmail
                .map(this::mapToUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException("User with username " + email + " was not found"));
    }


    private UserDetails mapToUserDetails(UserEntity userEntity) {
        return new User(
                userEntity.getEmail(),
                userEntity.getPassword(),
                extractAuthorities(userEntity));
    }

    private List<GrantedAuthority> extractAuthorities(UserEntity userEntity) {
        return userEntity
                .getRoles()
                .stream()
                .map(this::mapRole).toList();
    }

    private GrantedAuthority mapRole(UserRoleEntity userRoleEntity) {
        return new SimpleGrantedAuthority( "ROLE_"+userRoleEntity.getRole().name());
    }

}

































//package pmgkn.pescores.pescores.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import pmgkn.pescores.pescores.domain.entity.UserEntity;
//import pmgkn.pescores.pescores.domain.entity.UserRoleEntity;
//import pmgkn.pescores.pescores.repositories.UserRepository;
//
//import java.util.Collection;
//import java.util.Collections;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//public class PEUserDetailsService implements UserDetailsService {
//
//    private UserRepository userRepository;
//
//    @Autowired
//    public PEUserDetailsService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        UserEntity user = userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
//        return new User(user.getEmail(), user.getPassword(),mapRolesToAuthorities(Collections.singletonList(user.getRole())));
//    }
//
//    private Collection<GrantedAuthority> mapRolesToAuthorities(List<UserRoleEntity> roles) {
//        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRole().name())).collect(Collectors.toList());
//    }
//
//    public String getUsername() {
//        return null;
//    }
//}
//
//
