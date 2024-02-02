package pmgkn.pescores.pescores.service;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import pmgkn.pescores.pescores.domain.entity.UserRoleEntity;
import pmgkn.pescores.pescores.domain.entity.normatives.DenseBallEntity;
import pmgkn.pescores.pescores.domain.enums.GenderEnum;
import pmgkn.pescores.pescores.domain.enums.UserRoleEnum;
import pmgkn.pescores.pescores.repositories.DenseBallRepository;
import pmgkn.pescores.pescores.repositories.UserRoleRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class DbInitService {

    private final UserRoleRepository userRoleRepository;
    private final DenseBallRepository denseBallRepository;
//    private final PasswordEncoder passwordEncoder;
//    private final UserRepository userRepository;

    public DbInitService(UserRoleRepository userRoleRepository,
                         DenseBallRepository denseBallRepository){
//                                         PasswordEncoder passwordEncoder,
//                                         UserRepository userRepository) {
        this.userRoleRepository = userRoleRepository;
//        this.passwordEncoder = passwordEncoder;
//        this.userRepository = userRepository;
        this.denseBallRepository = denseBallRepository;
    }

    @PostConstruct
    public void init() {
        initRoles();
        initDenseBall();
//        initAdmin();
//        initWorkers();
//        initUsers();
    }

    private void initDenseBall() {
        if (this.denseBallRepository.count() == 0) {
            DenseBallEntity denseBallOne=new DenseBallEntity()
                    .setClassNum(8)
                    .setGender("MALE")
                    .setMax(BigDecimal.valueOf(3.55))
                    .setMin(BigDecimal.ZERO)
                    .setGrade(2);
            DenseBallEntity denseBallTwo=new DenseBallEntity()
                    .setClassNum(8)
                    .setGender("MALE")
                    .setMax(BigDecimal.valueOf(4.84))
                    .setMin(BigDecimal.valueOf(3.56))
                    .setGrade(3);
            DenseBallEntity denseBallThree=new DenseBallEntity()
                    .setClassNum(8)
                    .setGender("MALE")
                    .setMax(BigDecimal.valueOf(7.09))
                    .setMin(BigDecimal.valueOf(4.85))
                    .setGrade(4);
            DenseBallEntity denseBallFour=new DenseBallEntity()
                    .setClassNum(8)
                    .setGender("MALE")
                    .setMax(BigDecimal.valueOf(8.06))
                    .setMin(BigDecimal.valueOf(7.10))
                    .setGrade(5);
            DenseBallEntity denseBallFive=new DenseBallEntity()
                    .setClassNum(8)
                    .setGender("MALE")
                    .setMax(BigDecimal.valueOf(15))
                    .setMin(BigDecimal.valueOf(8.07))
                    .setGrade(6);
            this.denseBallRepository.saveAndFlush(denseBallOne);
            this.denseBallRepository.saveAndFlush(denseBallTwo);
            this.denseBallRepository.saveAndFlush(denseBallThree);
            this.denseBallRepository.saveAndFlush(denseBallFour);
            this.denseBallRepository.saveAndFlush(denseBallFive);
        }
    }

    public void initRoles() {
        if (this.userRoleRepository.count() == 0) {
            this.userRoleRepository.saveAllAndFlush(getUserRoles());
        }
    }

    private static List<UserRoleEntity> getUserRoles() {
        List<UserRoleEntity> roles = new ArrayList<>();

        roles.add(new UserRoleEntity().setRole(UserRoleEnum.USER));
        roles.add(new UserRoleEntity().setRole(UserRoleEnum.ADMIN));

        return roles;
    }

//    public void initAdmin() {
//        if (this.userRepository.count() == 0) {
//            UserEntity user = new UserEntity()
//                    .setFirstName("Admin")
//                    .setLastName("Adminov")
//                    .setAge(25)
//                    .setEmail("admin@admin.com")
//                    .setPassword(passwordEncoder.encode("secret1@"))
//                    .setGender(GenderEnum.FEMALE)
//                    .setUsername("admin")
//                    .setPhoneNumber("0515051501")
//                    .setRoles(userRoleRepository.findAll())
//                    .setCart(this.cartService.getNewCart());
//
//            this.userRepository.saveAndFlush(user);
//        }
//    }
//
//    public void initWorkers() {
//        if (this.userRepository.count() == 1) {
//
//            UserEntity workerOne = new UserEntity()
//                    .setFirstName("Worker")
//                    .setLastName("One")
//                    .setAge(25)
//                    .setEmail("worker@one.com")
//                    .setPassword(passwordEncoder.encode("secret1@"))
//                    .setGender(GenderEnum.MALE)
//                    .setUsername("workerOne")
//                    .setPhoneNumber("0515051502")
//                    .setRoles(userRoleRepository.findAll()
//                            .stream()
//                            .filter(r -> r.getRole() != UserRoleEnum.ADMIN)
//                            .collect(Collectors.toList()))
//                    .setCart(this.cartService.getNewCart());
//
//            UserEntity workerTwo = new UserEntity()
//                    .setFirstName("Worker")
//                    .setLastName("Two")
//                    .setAge(25)
//                    .setEmail("worker@two.com")
//                    .setPassword(passwordEncoder.encode("secret1@"))
//                    .setGender(GenderEnum.FEMALE)
//                    .setUsername("workerTwo")
//                    .setPhoneNumber("0515051503")
//                    .setRoles(userRoleRepository.findAll()
//                            .stream()
//                            .filter(r -> r.getRole() != UserRoleEnum.ADMIN)
//                            .collect(Collectors.toList()))
//                    .setCart(this.cartService.getNewCart());
//
//            this.userRepository.saveAndFlush(workerOne);
//            this.userRepository.saveAndFlush(workerTwo);
//        }
//
//    }
//
//    private void initUsers() {
//        if (this.userRepository.count() == 3) {
//
//            UserEntity userOne = new UserEntity()
//                    .setFirstName("Nikol")
//                    .setLastName("Georgieva")
//                    .setAge(25)
//                    .setEmail("nikol@user.com")
//                    .setPassword(passwordEncoder.encode("secret1@"))
//                    .setGender(GenderEnum.FEMALE)
//                    .setUsername("nikol")
//                    .setPhoneNumber("0515051504")
//                    .setRoles(userRoleRepository.findAll()
//                            .stream()
//                            .filter(r -> r.getRole() == UserRoleEnum.USER)
//                            .collect(Collectors.toList()))
//                    .setCart(this.cartService.getNewCart());
//
//            UserEntity userTwo = new UserEntity()
//                    .setFirstName("Ivan")
//                    .setLastName("Ivanov")
//                    .setAge(25)
//                    .setEmail("ivan@user.com")
//                    .setPassword(passwordEncoder.encode("secret1@"))
//                    .setGender(GenderEnum.MALE)
//                    .setUsername("ivan")
//                    .setPhoneNumber("0515051504")
//                    .setRoles(userRoleRepository.findAll()
//                            .stream()
//                            .filter(r -> r.getRole() == UserRoleEnum.USER)
//                            .collect(Collectors.toList()))
//                    .setCart(this.cartService.getNewCart());
//
//            UserEntity testUser = new UserEntity()
//                    .setFirstName("Testerche")
//                    .setLastName("Testerov")
//                    .setAge(25)
//                    .setEmail("testAppUser@user.com")
//                    .setPassword(passwordEncoder.encode("secret1@"))
//                    .setGender(GenderEnum.MALE)
//                    .setUsername("testAppUser")
//                    .setPhoneNumber("0515051504")
//                    .setRoles(userRoleRepository.findAll()
//                            .stream()
//                            .filter(r -> r.getRole() == UserRoleEnum.USER)
//                            .collect(Collectors.toList()))
//                    .setCart(this.cartService.getNewCart());
//
//            this.userRepository.saveAndFlush(userOne);
//            this.userRepository.saveAndFlush(userTwo);
//            this.userRepository.saveAndFlush(testUser);
//        }
//    }

}
