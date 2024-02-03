package pmgkn.pescores.pescores.service;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import pmgkn.pescores.pescores.domain.entity.UserRoleEntity;
import pmgkn.pescores.pescores.domain.entity.normatives.DenseBallEntity;
import pmgkn.pescores.pescores.domain.entity.normatives.JumpEntity;
import pmgkn.pescores.pescores.domain.enums.UserRoleEnum;
import pmgkn.pescores.pescores.repositories.DenseBallRepository;
import pmgkn.pescores.pescores.repositories.JumpRepository;
import pmgkn.pescores.pescores.repositories.UserRoleRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static pmgkn.pescores.pescores.domain.entity.constants.Messages.FEMALE;
import static pmgkn.pescores.pescores.domain.entity.constants.Messages.MALE;

@Service
public class DbInitService {

    private final UserRoleRepository userRoleRepository;
    private final DenseBallRepository denseBallRepository;

    private final JumpRepository jumpRepository;

    public DbInitService(UserRoleRepository userRoleRepository,
                         DenseBallRepository denseBallRepository,
                         JumpRepository jumpRepository) {
        this.userRoleRepository = userRoleRepository;
        this.denseBallRepository = denseBallRepository;
        this.jumpRepository = jumpRepository;
    }

    @PostConstruct
    public void init() {
        initRoles();
        initDenseBall();
        initJump();
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

    private void initDenseBall() {
        if (this.denseBallRepository.count() == 0) {
            denseBallSixthGrade();
            denseBallFifthGrade();
            denseBallSeventhGrade();
            denseBallEightGrade();
            denseBallNinthGrade();
            denseBallTenthGrade();
            denseBallEleventhGrade();
            denseBallTwelfthGrade();
        }
    }

    private void initJump() {
        if (this.jumpRepository.count() == 0) {
           jumpFifthGrade();
           jumpSixthGrade();
           jumpSeventhGrade();
           jumpEightGrade();
           jumpNinthGrade();
           jumpTenthGrade();
           jumpEleventhGrade();
           jumpTwelfthGrade();
        }
    }

    private static DenseBallEntity getDenseBallEntity(Integer classNum,
                                                      String gender,
                                                      BigDecimal max,
                                                      BigDecimal min,
                                                      int grade) {
        return new DenseBallEntity()
                .setClassNum(classNum)
                .setGender(gender)
                .setMax(max)
                .setMin(min)
                .setGrade(grade);
    }

    private static JumpEntity getJumpEntity(Integer classNum,
                                            String gender,
                                            BigDecimal max,
                                            BigDecimal min,
                                            int grade) {
        return new JumpEntity()
                .setClassNum(classNum)
                .setGender(gender)
                .setMax(max)
                .setMin(min)
                .setGrade(grade);
    }

    private void jumpFifthGrade() {
        List<JumpEntity> jumpFifthGrade = new ArrayList<>();
        jumpFifthGrade.add(getJumpEntity(5, MALE, BigDecimal.valueOf(1.12), BigDecimal.ZERO, 2));
        jumpFifthGrade.add(getJumpEntity(5, MALE, BigDecimal.valueOf(1.35), BigDecimal.valueOf(1.13), 3));
        jumpFifthGrade.add(getJumpEntity(5, MALE, BigDecimal.valueOf(1.76), BigDecimal.valueOf(1.36), 4));
        jumpFifthGrade.add(getJumpEntity(5, MALE, BigDecimal.valueOf(1.93), BigDecimal.valueOf(1.77), 5));
        jumpFifthGrade.add(getJumpEntity(5, MALE, BigDecimal.valueOf(5), BigDecimal.valueOf(1.94), 6));
        jumpFifthGrade.add(getJumpEntity(5, FEMALE, BigDecimal.valueOf(1.02), BigDecimal.ZERO, 2));
        jumpFifthGrade.add(getJumpEntity(5, FEMALE, BigDecimal.valueOf(1.25), BigDecimal.valueOf(1.03), 3));
        jumpFifthGrade.add(getJumpEntity(5, FEMALE, BigDecimal.valueOf(1.65), BigDecimal.valueOf(1.26), 4));
        jumpFifthGrade.add(getJumpEntity(5, FEMALE, BigDecimal.valueOf(1.82), BigDecimal.valueOf(1.66), 5));
        jumpFifthGrade.add(getJumpEntity(5, FEMALE, BigDecimal.valueOf(5), BigDecimal.valueOf(1.83), 6));
        this.jumpRepository.saveAllAndFlush(jumpFifthGrade);
    }

    private void jumpSixthGrade() {
        List<JumpEntity> jumpSixthGrade = new ArrayList<>();
        jumpSixthGrade.add(getJumpEntity(6, MALE, BigDecimal.valueOf(1.19), BigDecimal.ZERO, 2));
        jumpSixthGrade.add(getJumpEntity(6, MALE, BigDecimal.valueOf(1.43), BigDecimal.valueOf(1.20), 3));
        jumpSixthGrade.add(getJumpEntity(6, MALE, BigDecimal.valueOf(1.84), BigDecimal.valueOf(1.44), 4));
        jumpSixthGrade.add(getJumpEntity(6, MALE, BigDecimal.valueOf(2.02), BigDecimal.valueOf(1.85), 5));
        jumpSixthGrade.add(getJumpEntity(6, MALE, BigDecimal.valueOf(5), BigDecimal.valueOf(2.03), 6));
        jumpSixthGrade.add(getJumpEntity(6, FEMALE, BigDecimal.valueOf(1.06), BigDecimal.ZERO, 2));
        jumpSixthGrade.add(getJumpEntity(6, FEMALE, BigDecimal.valueOf(1.30), BigDecimal.valueOf(1.07), 3));
        jumpSixthGrade.add(getJumpEntity(6, FEMALE, BigDecimal.valueOf(1.71), BigDecimal.valueOf(1.31), 4));
        jumpSixthGrade.add(getJumpEntity(6, FEMALE, BigDecimal.valueOf(1.89), BigDecimal.valueOf(1.72), 5));
        jumpSixthGrade.add(getJumpEntity(6, FEMALE, BigDecimal.valueOf(5), BigDecimal.valueOf(1.90), 6));
        this.jumpRepository.saveAllAndFlush(jumpSixthGrade);
    }

    private void jumpSeventhGrade() {
        List<JumpEntity> jumpSeventhGrade = new ArrayList<>();
        jumpSeventhGrade.add(getJumpEntity(7, MALE, BigDecimal.valueOf(1.25), BigDecimal.ZERO, 2));
        jumpSeventhGrade.add(getJumpEntity(7, MALE, BigDecimal.valueOf(1.50), BigDecimal.valueOf(1.26), 3));
        jumpSeventhGrade.add(getJumpEntity(7, MALE, BigDecimal.valueOf(1.94), BigDecimal.valueOf(1.51), 4));
        jumpSeventhGrade.add(getJumpEntity(7, MALE, BigDecimal.valueOf(2.12), BigDecimal.valueOf(1.95), 5));
        jumpSeventhGrade.add(getJumpEntity(7, MALE, BigDecimal.valueOf(5), BigDecimal.valueOf(2.13), 6));
        jumpSeventhGrade.add(getJumpEntity(7, FEMALE, BigDecimal.valueOf(1.08), BigDecimal.ZERO, 2));
        jumpSeventhGrade.add(getJumpEntity(7, FEMALE, BigDecimal.valueOf(1.33), BigDecimal.valueOf(1.09), 3));
        jumpSeventhGrade.add(getJumpEntity(7, FEMALE, BigDecimal.valueOf(1.75), BigDecimal.valueOf(1.34), 4));
        jumpSeventhGrade.add(getJumpEntity(7, FEMALE, BigDecimal.valueOf(1.93), BigDecimal.valueOf(1.76), 5));
        jumpSeventhGrade.add(getJumpEntity(7, FEMALE, BigDecimal.valueOf(5), BigDecimal.valueOf(1.94), 6));
        this.jumpRepository.saveAllAndFlush(jumpSeventhGrade);
    }

    private void jumpEightGrade() {
        List<JumpEntity> jumpEightGrade = new ArrayList<>();
        jumpEightGrade.add(getJumpEntity(8, MALE, BigDecimal.valueOf(1.33), BigDecimal.ZERO, 2));
        jumpEightGrade.add(getJumpEntity(8, MALE, BigDecimal.valueOf(1.59), BigDecimal.valueOf(1.34), 3));
        jumpEightGrade.add(getJumpEntity(8, MALE, BigDecimal.valueOf(2.04), BigDecimal.valueOf(1.60), 4));
        jumpEightGrade.add(getJumpEntity(8, MALE, BigDecimal.valueOf(2.24), BigDecimal.valueOf(2.05), 5));
        jumpEightGrade.add(getJumpEntity(8, MALE, BigDecimal.valueOf(5), BigDecimal.valueOf(2.25), 6));
        jumpEightGrade.add(getJumpEntity(8, FEMALE, BigDecimal.valueOf(1.10), BigDecimal.ZERO, 2));
        jumpEightGrade.add(getJumpEntity(8, FEMALE, BigDecimal.valueOf(1.35), BigDecimal.valueOf(1.11), 3));
        jumpEightGrade.add(getJumpEntity(8, FEMALE, BigDecimal.valueOf(1.78), BigDecimal.valueOf(1.36), 4));
        jumpEightGrade.add(getJumpEntity(8, FEMALE, BigDecimal.valueOf(1.97), BigDecimal.valueOf(1.79), 5));
        jumpEightGrade.add(getJumpEntity(8, FEMALE, BigDecimal.valueOf(5), BigDecimal.valueOf(1.98), 6));
        this.jumpRepository.saveAllAndFlush(jumpEightGrade);
    }

    private void jumpNinthGrade() {
        List<JumpEntity> jumpNinthGrade = new ArrayList<>();
        jumpNinthGrade.add(getJumpEntity(9, MALE, BigDecimal.valueOf(1.40), BigDecimal.ZERO, 2));
        jumpNinthGrade.add(getJumpEntity(9, MALE, BigDecimal.valueOf(1.66), BigDecimal.valueOf(1.41), 3));
        jumpNinthGrade.add(getJumpEntity(9, MALE, BigDecimal.valueOf(2.13), BigDecimal.valueOf(1.67), 4));
        jumpNinthGrade.add(getJumpEntity(9, MALE, BigDecimal.valueOf(2.34), BigDecimal.valueOf(2.14), 5));
        jumpNinthGrade.add(getJumpEntity(9, MALE, BigDecimal.valueOf(5), BigDecimal.valueOf(2.35), 6));
        jumpNinthGrade.add(getJumpEntity(9, FEMALE, BigDecimal.valueOf(1.12), BigDecimal.ZERO, 2));
        jumpNinthGrade.add(getJumpEntity(9, FEMALE, BigDecimal.valueOf(1.37), BigDecimal.valueOf(1.13), 3));
        jumpNinthGrade.add(getJumpEntity(9, FEMALE, BigDecimal.valueOf(1.81), BigDecimal.valueOf(1.38), 4));
        jumpNinthGrade.add(getJumpEntity(9, FEMALE, BigDecimal.valueOf(2.00), BigDecimal.valueOf(1.82), 5));
        jumpNinthGrade.add(getJumpEntity(9, FEMALE, BigDecimal.valueOf(5), BigDecimal.valueOf(2.01), 6));
        this.jumpRepository.saveAllAndFlush(jumpNinthGrade);
    }

    private void jumpTenthGrade() {
        List<JumpEntity> jumpTenthGrade = new ArrayList<>();
        jumpTenthGrade.add(getJumpEntity(10, MALE, BigDecimal.valueOf(1.46), BigDecimal.ZERO, 2));
        jumpTenthGrade.add(getJumpEntity(10, MALE, BigDecimal.valueOf(1.74), BigDecimal.valueOf(1.47), 3));
        jumpTenthGrade.add(getJumpEntity(10, MALE, BigDecimal.valueOf(2.22), BigDecimal.valueOf(1.75), 4));
        jumpTenthGrade.add(getJumpEntity(10, MALE, BigDecimal.valueOf(2.43), BigDecimal.valueOf(2.23), 5));
        jumpTenthGrade.add(getJumpEntity(10, MALE, BigDecimal.valueOf(5), BigDecimal.valueOf(2.44), 6));
        jumpTenthGrade.add(getJumpEntity(10, FEMALE, BigDecimal.valueOf(1.12), BigDecimal.ZERO, 2));
        jumpTenthGrade.add(getJumpEntity(10, FEMALE, BigDecimal.valueOf(1.38), BigDecimal.valueOf(1.13), 3));
        jumpTenthGrade.add(getJumpEntity(10, FEMALE, BigDecimal.valueOf(1.82), BigDecimal.valueOf(1.39), 4));
        jumpTenthGrade.add(getJumpEntity(10, FEMALE, BigDecimal.valueOf(2.01), BigDecimal.valueOf(1.83), 5));
        jumpTenthGrade.add(getJumpEntity(10, FEMALE, BigDecimal.valueOf(5), BigDecimal.valueOf(2.02), 6));
        this.jumpRepository.saveAllAndFlush(jumpTenthGrade);
    }

    private void jumpEleventhGrade() {
        List<JumpEntity> jumpEleventhGrade = new ArrayList<>();
        jumpEleventhGrade.add(getJumpEntity(11, MALE, BigDecimal.valueOf(1.52), BigDecimal.ZERO, 2));
        jumpEleventhGrade.add(getJumpEntity(11, MALE, BigDecimal.valueOf(1.80), BigDecimal.valueOf(1.53), 3));
        jumpEleventhGrade.add(getJumpEntity(11, MALE, BigDecimal.valueOf(2.30), BigDecimal.valueOf(1.81), 4));
        jumpEleventhGrade.add(getJumpEntity(11, MALE, BigDecimal.valueOf(2.51), BigDecimal.valueOf(2.31), 5));
        jumpEleventhGrade.add(getJumpEntity(11, MALE, BigDecimal.valueOf(5), BigDecimal.valueOf(2.52), 6));
        jumpEleventhGrade.add(getJumpEntity(11, FEMALE, BigDecimal.valueOf(1.12), BigDecimal.ZERO, 2));
        jumpEleventhGrade.add(getJumpEntity(11, FEMALE, BigDecimal.valueOf(1.38), BigDecimal.valueOf(1.13), 3));
        jumpEleventhGrade.add(getJumpEntity(11, FEMALE, BigDecimal.valueOf(1.82), BigDecimal.valueOf(1.39), 4));
        jumpEleventhGrade.add(getJumpEntity(11, FEMALE, BigDecimal.valueOf(2.01), BigDecimal.valueOf(1.83), 5));
        jumpEleventhGrade.add(getJumpEntity(11, FEMALE, BigDecimal.valueOf(5), BigDecimal.valueOf(2.02), 6));
        this.jumpRepository.saveAllAndFlush(jumpEleventhGrade);
    }

    private void jumpTwelfthGrade() {
        List<JumpEntity> jumpTwelfthGrade = new ArrayList<>();
        jumpTwelfthGrade.add(getJumpEntity(12, MALE, BigDecimal.valueOf(1.55), BigDecimal.ZERO, 2));
        jumpTwelfthGrade.add(getJumpEntity(12, MALE, BigDecimal.valueOf(1.84), BigDecimal.valueOf(1.56), 3));
        jumpTwelfthGrade.add(getJumpEntity(12, MALE, BigDecimal.valueOf(2.35), BigDecimal.valueOf(1.85), 4));
        jumpTwelfthGrade.add(getJumpEntity(12, MALE, BigDecimal.valueOf(2.57), BigDecimal.valueOf(2.36), 5));
        jumpTwelfthGrade.add(getJumpEntity(12, MALE, BigDecimal.valueOf(5), BigDecimal.valueOf(2.58), 6));
        jumpTwelfthGrade.add(getJumpEntity(12, FEMALE, BigDecimal.valueOf(1.12), BigDecimal.ZERO, 2));
        jumpTwelfthGrade.add(getJumpEntity(12, FEMALE, BigDecimal.valueOf(1.38), BigDecimal.valueOf(1.13), 3));
        jumpTwelfthGrade.add(getJumpEntity(12, FEMALE, BigDecimal.valueOf(1.82), BigDecimal.valueOf(1.39), 4));
        jumpTwelfthGrade.add(getJumpEntity(12, FEMALE, BigDecimal.valueOf(2.01), BigDecimal.valueOf(1.83), 5));
        jumpTwelfthGrade.add(getJumpEntity(12, FEMALE, BigDecimal.valueOf(5), BigDecimal.valueOf(2.02), 6));
        this.jumpRepository.saveAllAndFlush(jumpTwelfthGrade);
    }

    private void denseBallFifthGrade() {
        List<DenseBallEntity> denseBallFifthGrade = new ArrayList<>();
        denseBallFifthGrade.add(getDenseBallEntity(5, MALE, BigDecimal.valueOf(2.19), BigDecimal.ZERO, 2));
        denseBallFifthGrade.add(getDenseBallEntity(5, MALE, BigDecimal.valueOf(2.99), BigDecimal.valueOf(2.20), 3));
        denseBallFifthGrade.add(getDenseBallEntity(5, MALE, BigDecimal.valueOf(4.39), BigDecimal.valueOf(3.00), 4));
        denseBallFifthGrade.add(getDenseBallEntity(5, MALE, BigDecimal.valueOf(4.99), BigDecimal.valueOf(4.40), 5));
        denseBallFifthGrade.add(getDenseBallEntity(5, MALE, BigDecimal.valueOf(25), BigDecimal.valueOf(5.00), 6));
        denseBallFifthGrade.add(getDenseBallEntity(5, FEMALE, BigDecimal.valueOf(2.41), BigDecimal.ZERO, 2));
        denseBallFifthGrade.add(getDenseBallEntity(5, FEMALE, BigDecimal.valueOf(3.28), BigDecimal.valueOf(2.42), 3));
        denseBallFifthGrade.add(getDenseBallEntity(5, FEMALE, BigDecimal.valueOf(4.81), BigDecimal.valueOf(3.29), 4));
        denseBallFifthGrade.add(getDenseBallEntity(5, FEMALE, BigDecimal.valueOf(5.47), BigDecimal.valueOf(4.82), 5));
        denseBallFifthGrade.add(getDenseBallEntity(5, FEMALE, BigDecimal.valueOf(25), BigDecimal.valueOf(5.48), 6));
        this.denseBallRepository.saveAllAndFlush(denseBallFifthGrade);
    }

    private void denseBallSixthGrade() {
        List<DenseBallEntity> denseBallSixthGrade = new ArrayList<>();
        denseBallSixthGrade.add(getDenseBallEntity(6, MALE, BigDecimal.valueOf(2.64), BigDecimal.ZERO, 2));
        denseBallSixthGrade.add(getDenseBallEntity(6, MALE, BigDecimal.valueOf(3.60), BigDecimal.valueOf(2.65), 3));
        denseBallSixthGrade.add(getDenseBallEntity(6, MALE, BigDecimal.valueOf(5.29), BigDecimal.valueOf(3.61), 4));
        denseBallSixthGrade.add(getDenseBallEntity(6, MALE, BigDecimal.valueOf(6.01), BigDecimal.valueOf(5.30), 5));
        denseBallSixthGrade.add(getDenseBallEntity(6, MALE, BigDecimal.valueOf(25), BigDecimal.valueOf(6.02), 6));
        denseBallSixthGrade.add(getDenseBallEntity(6, FEMALE, BigDecimal.valueOf(2.41), BigDecimal.ZERO, 2));
        denseBallSixthGrade.add(getDenseBallEntity(6, FEMALE, BigDecimal.valueOf(3.28), BigDecimal.valueOf(2.42), 3));
        denseBallSixthGrade.add(getDenseBallEntity(6, FEMALE, BigDecimal.valueOf(4.81), BigDecimal.valueOf(3.29), 4));
        denseBallSixthGrade.add(getDenseBallEntity(6, FEMALE, BigDecimal.valueOf(5.47), BigDecimal.valueOf(4.82), 5));
        denseBallSixthGrade.add(getDenseBallEntity(6, FEMALE, BigDecimal.valueOf(25), BigDecimal.valueOf(5.48), 6));
        this.denseBallRepository.saveAllAndFlush(denseBallSixthGrade);
    }

    private void denseBallSeventhGrade() {
        List<DenseBallEntity> denseBallSeventhGrade = new ArrayList<>();
        denseBallSeventhGrade.add(getDenseBallEntity(7, MALE, BigDecimal.valueOf(3.10), BigDecimal.ZERO, 2));
        denseBallSeventhGrade.add(getDenseBallEntity(7, MALE, BigDecimal.valueOf(4.22), BigDecimal.valueOf(3.11), 3));
        denseBallSeventhGrade.add(getDenseBallEntity(7, MALE, BigDecimal.valueOf(6.19), BigDecimal.valueOf(4.23), 4));
        denseBallSeventhGrade.add(getDenseBallEntity(7, MALE, BigDecimal.valueOf(7.04), BigDecimal.valueOf(6.20), 5));
        denseBallSeventhGrade.add(getDenseBallEntity(7, MALE, BigDecimal.valueOf(25), BigDecimal.valueOf(7.05), 6));
        denseBallSeventhGrade.add(getDenseBallEntity(7, FEMALE, BigDecimal.valueOf(2.64), BigDecimal.ZERO, 2));
        denseBallSeventhGrade.add(getDenseBallEntity(7, FEMALE, BigDecimal.valueOf(3.64), BigDecimal.valueOf(2.65), 3));
        denseBallSeventhGrade.add(getDenseBallEntity(7, FEMALE, BigDecimal.valueOf(5.40), BigDecimal.valueOf(3.65), 4));
        denseBallSeventhGrade.add(getDenseBallEntity(7, FEMALE, BigDecimal.valueOf(6.15), BigDecimal.valueOf(5.41), 5));
        denseBallSeventhGrade.add(getDenseBallEntity(7, FEMALE, BigDecimal.valueOf(25), BigDecimal.valueOf(6.16), 6));
        this.denseBallRepository.saveAllAndFlush(denseBallSeventhGrade);
    }

    private void denseBallEightGrade() {
        List<DenseBallEntity> denseBallEightGrade = new ArrayList<>();
        denseBallEightGrade.add(getDenseBallEntity(8, MALE, BigDecimal.valueOf(3.55), BigDecimal.ZERO, 2));
        denseBallEightGrade.add(getDenseBallEntity(8, MALE, BigDecimal.valueOf(4.84), BigDecimal.valueOf(3.56), 3));
        denseBallEightGrade.add(getDenseBallEntity(8, MALE, BigDecimal.valueOf(7.09), BigDecimal.valueOf(4.85), 4));
        denseBallEightGrade.add(getDenseBallEntity(8, MALE, BigDecimal.valueOf(8.06), BigDecimal.valueOf(7.10), 5));
        denseBallEightGrade.add(getDenseBallEntity(8, MALE, BigDecimal.valueOf(25), BigDecimal.valueOf(8.07), 6));
        denseBallEightGrade.add(getDenseBallEntity(8, FEMALE, BigDecimal.valueOf(2.80), BigDecimal.ZERO, 2));
        denseBallEightGrade.add(getDenseBallEntity(8, FEMALE, BigDecimal.valueOf(3.89), BigDecimal.valueOf(2.81), 3));
        denseBallEightGrade.add(getDenseBallEntity(8, FEMALE, BigDecimal.valueOf(5.81), BigDecimal.valueOf(3.90), 4));
        denseBallEightGrade.add(getDenseBallEntity(8, FEMALE, BigDecimal.valueOf(6.63), BigDecimal.valueOf(5.82), 5));
        denseBallEightGrade.add(getDenseBallEntity(8, FEMALE, BigDecimal.valueOf(25), BigDecimal.valueOf(6.64), 6));
        this.denseBallRepository.saveAllAndFlush(denseBallEightGrade);
    }

    private void denseBallNinthGrade() {
        List<DenseBallEntity> denseBallNinthGrade = new ArrayList<>();
        denseBallNinthGrade.add(getDenseBallEntity(9, MALE, BigDecimal.valueOf(3.94), BigDecimal.ZERO, 2));
        denseBallNinthGrade.add(getDenseBallEntity(9, MALE, BigDecimal.valueOf(5.37), BigDecimal.valueOf(3.95), 3));
        denseBallNinthGrade.add(getDenseBallEntity(9, MALE, BigDecimal.valueOf(7.87), BigDecimal.valueOf(5.38), 4));
        denseBallNinthGrade.add(getDenseBallEntity(9, MALE, BigDecimal.valueOf(8.94), BigDecimal.valueOf(7.88), 5));
        denseBallNinthGrade.add(getDenseBallEntity(9, MALE, BigDecimal.valueOf(25), BigDecimal.valueOf(8.95), 6));
        denseBallNinthGrade.add(getDenseBallEntity(9, FEMALE, BigDecimal.valueOf(2.91), BigDecimal.ZERO, 2));
        denseBallNinthGrade.add(getDenseBallEntity(9, FEMALE, BigDecimal.valueOf(4.07), BigDecimal.valueOf(2.92), 3));
        denseBallNinthGrade.add(getDenseBallEntity(9, FEMALE, BigDecimal.valueOf(6.10), BigDecimal.valueOf(4.08), 4));
        denseBallNinthGrade.add(getDenseBallEntity(9, FEMALE, BigDecimal.valueOf(6.97), BigDecimal.valueOf(6.11), 5));
        denseBallNinthGrade.add(getDenseBallEntity(9, FEMALE, BigDecimal.valueOf(25), BigDecimal.valueOf(6.98), 6));
        this.denseBallRepository.saveAllAndFlush(denseBallNinthGrade);
    }

    private void denseBallTenthGrade() {
        List<DenseBallEntity> denseBallTenthGrade = new ArrayList<>();
        denseBallTenthGrade.add(getDenseBallEntity(10, MALE, BigDecimal.valueOf(4.27), BigDecimal.ZERO, 2));
        denseBallTenthGrade.add(getDenseBallEntity(10, MALE, BigDecimal.valueOf(5.82), BigDecimal.valueOf(4.28), 3));
        denseBallTenthGrade.add(getDenseBallEntity(10, MALE, BigDecimal.valueOf(8.53), BigDecimal.valueOf(5.83), 4));
        denseBallTenthGrade.add(getDenseBallEntity(10, MALE, BigDecimal.valueOf(9.69), BigDecimal.valueOf(8.54), 5));
        denseBallTenthGrade.add(getDenseBallEntity(10, MALE, BigDecimal.valueOf(25), BigDecimal.valueOf(9.70),6));
        denseBallTenthGrade.add(getDenseBallEntity(10, FEMALE, BigDecimal.valueOf(2.99), BigDecimal.ZERO, 2));
        denseBallTenthGrade.add(getDenseBallEntity(10, FEMALE, BigDecimal.valueOf(4.19), BigDecimal.valueOf(3.00), 3));
        denseBallTenthGrade.add(getDenseBallEntity(10, FEMALE, BigDecimal.valueOf(6.29), BigDecimal.valueOf(4.20), 4));
        denseBallTenthGrade.add(getDenseBallEntity(10, FEMALE, BigDecimal.valueOf(7.19), BigDecimal.valueOf(6.30), 5));
        denseBallTenthGrade.add(getDenseBallEntity(10, FEMALE, BigDecimal.valueOf(25), BigDecimal.valueOf(7.20), 6));
        this.denseBallRepository.saveAllAndFlush(denseBallTenthGrade);
    }

    private void denseBallEleventhGrade() {
        List<DenseBallEntity> denseBallEleventhGrade = new ArrayList<>();
        denseBallEleventhGrade.add(getDenseBallEntity(11, MALE, BigDecimal.valueOf(4.51), BigDecimal.ZERO, 2));
        denseBallEleventhGrade.add(getDenseBallEntity(11, MALE, BigDecimal.valueOf(6.15), BigDecimal.valueOf(4.52), 3));
        denseBallEleventhGrade.add(getDenseBallEntity(11, MALE, BigDecimal.valueOf(9.01), BigDecimal.valueOf(6.16), 4));
        denseBallEleventhGrade.add(getDenseBallEntity(11, MALE, BigDecimal.valueOf(10.24), BigDecimal.valueOf(9.02), 5));
        denseBallEleventhGrade.add(getDenseBallEntity(11, MALE, BigDecimal.valueOf(25), BigDecimal.valueOf(10.25), 6));
        denseBallEleventhGrade.add(getDenseBallEntity(11, FEMALE, BigDecimal.valueOf(2.99), BigDecimal.ZERO, 2));
        denseBallEleventhGrade.add(getDenseBallEntity(11, FEMALE, BigDecimal.valueOf(4.19), BigDecimal.valueOf(3.00), 3));
        denseBallEleventhGrade.add(getDenseBallEntity(11, FEMALE, BigDecimal.valueOf(6.29), BigDecimal.valueOf(4.20), 4));
        denseBallEleventhGrade.add(getDenseBallEntity(11, FEMALE, BigDecimal.valueOf(7.19), BigDecimal.valueOf(6.30), 5));
        denseBallEleventhGrade.add(getDenseBallEntity(11, FEMALE, BigDecimal.valueOf(25), BigDecimal.valueOf(7.20), 6));
        this.denseBallRepository.saveAllAndFlush(denseBallEleventhGrade);
    }

    private void denseBallTwelfthGrade() {
        List<DenseBallEntity> denseBallTwelfthGrade = new ArrayList<>();
        denseBallTwelfthGrade.add(getDenseBallEntity(12, MALE, BigDecimal.valueOf(4.65), BigDecimal.ZERO, 2));
        denseBallTwelfthGrade.add(getDenseBallEntity(12, MALE, BigDecimal.valueOf(6.33), BigDecimal.valueOf(4.66), 3));
        denseBallTwelfthGrade.add(getDenseBallEntity(12, MALE, BigDecimal.valueOf(9.28), BigDecimal.valueOf(6.34), 4));
        denseBallTwelfthGrade.add(getDenseBallEntity(12, MALE, BigDecimal.valueOf(10.54), BigDecimal.valueOf(9.29), 5));
        denseBallTwelfthGrade.add(getDenseBallEntity(12, MALE, BigDecimal.valueOf(25), BigDecimal.valueOf(10.55), 6));
        denseBallTwelfthGrade.add(getDenseBallEntity(12, FEMALE, BigDecimal.valueOf(2.99), BigDecimal.ZERO, 2));
        denseBallTwelfthGrade.add(getDenseBallEntity(12, FEMALE, BigDecimal.valueOf(4.19), BigDecimal.valueOf(3.00), 3));
        denseBallTwelfthGrade.add(getDenseBallEntity(12, FEMALE, BigDecimal.valueOf(6.29), BigDecimal.valueOf(4.20), 4));
        denseBallTwelfthGrade.add(getDenseBallEntity(12, FEMALE, BigDecimal.valueOf(7.19), BigDecimal.valueOf(6.30), 5));
        denseBallTwelfthGrade.add(getDenseBallEntity(12, FEMALE, BigDecimal.valueOf(25), BigDecimal.valueOf(7.20), 6));
        this.denseBallRepository.saveAllAndFlush(denseBallTwelfthGrade);
    }




}
