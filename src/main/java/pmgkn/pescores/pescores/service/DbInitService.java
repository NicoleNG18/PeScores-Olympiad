package pmgkn.pescores.pescores.service;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import pmgkn.pescores.pescores.domain.entity.UserRoleEntity;
import pmgkn.pescores.pescores.domain.entity.normatives.DenseBallEntity;
import pmgkn.pescores.pescores.domain.entity.normatives.JumpEntity;
import pmgkn.pescores.pescores.domain.entity.normatives.TTestEntity;
import pmgkn.pescores.pescores.domain.enums.UserRoleEnum;
import pmgkn.pescores.pescores.repositories.*;

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

    private final TTestRepository tTestRepository;

    private final ThirtyMetersRepository thirtyMetersRepository;

    private final TwoHundredMetersRepository twoHundredMetersRepository;

    public DbInitService(UserRoleRepository userRoleRepository,
                         DenseBallRepository denseBallRepository,
                         JumpRepository jumpRepository,
                         TTestRepository tTestRepository,
                         ThirtyMetersRepository thirtyMetersRepository,
                         TwoHundredMetersRepository twoHundredMetersRepository) {
        this.userRoleRepository = userRoleRepository;
        this.denseBallRepository = denseBallRepository;
        this.jumpRepository = jumpRepository;
        this.tTestRepository = tTestRepository;
        this.thirtyMetersRepository = thirtyMetersRepository;
        this.twoHundredMetersRepository = twoHundredMetersRepository;
    }

    @PostConstruct
    public void init() {
        initRoles();
        initDenseBall();
        initJump();
        initTTest();
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

    private void initTTest() {
        if (this.tTestRepository.count() == 0) {
            tTestFifthGrade();
            tTestSixthGrade();
            tTestSeventhGrade();
            tTestEightGrade();
            tTestNinthGrade();
            tTestTenthGrade();
            tTestEleventhGrade();
            tTestTwelfthGrade();
        }
    }

    private static TTestEntity getTTestEntity(Integer classNum,
                                              String gender,
                                              BigDecimal max,
                                              BigDecimal min,
                                              int grade) {
        return new TTestEntity()
                .setClassNum(classNum)
                .setGender(gender)
                .setMax(max)
                .setMin(min)
                .setGrade(grade);
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


    private void tTestFifthGrade() {
        List<TTestEntity> tTestFifthGrade = new ArrayList<>();
        tTestFifthGrade.add(getTTestEntity(5, MALE, BigDecimal.valueOf(100.00), BigDecimal.valueOf(21.56), 2));
        tTestFifthGrade.add(getTTestEntity(5, MALE, BigDecimal.valueOf(21.55), BigDecimal.valueOf(19.41), 3));
        tTestFifthGrade.add(getTTestEntity(5, MALE, BigDecimal.valueOf(19.40), BigDecimal.valueOf(15.64), 4));
        tTestFifthGrade.add(getTTestEntity(5, MALE, BigDecimal.valueOf(15.63), BigDecimal.valueOf(14.03), 5));
        tTestFifthGrade.add(getTTestEntity(5, MALE, BigDecimal.valueOf(14.02), BigDecimal.ZERO, 6));
        tTestFifthGrade.add(getTTestEntity(5, FEMALE, BigDecimal.valueOf(100.00), BigDecimal.valueOf(22.71), 2));
        tTestFifthGrade.add(getTTestEntity(5, FEMALE, BigDecimal.valueOf(22.70), BigDecimal.valueOf(20.37), 3));
        tTestFifthGrade.add(getTTestEntity(5, FEMALE, BigDecimal.valueOf(20.36), BigDecimal.valueOf(16.28), 4));
        tTestFifthGrade.add(getTTestEntity(5, FEMALE, BigDecimal.valueOf(16.27), BigDecimal.valueOf(14.52), 5));
        tTestFifthGrade.add(getTTestEntity(5, FEMALE, BigDecimal.valueOf(14.51), BigDecimal.ZERO, 6));
        this.tTestRepository.saveAllAndFlush(tTestFifthGrade);
    }

    private void tTestSixthGrade() {
        List<TTestEntity> tTestSixthGrade = new ArrayList<>();
        tTestSixthGrade.add(getTTestEntity(6, MALE, BigDecimal.valueOf(100.00), BigDecimal.valueOf(21.04), 2));
        tTestSixthGrade.add(getTTestEntity(6, MALE, BigDecimal.valueOf(21.03), BigDecimal.valueOf(18.93), 3));
        tTestSixthGrade.add(getTTestEntity(6, MALE, BigDecimal.valueOf(18.92), BigDecimal.valueOf(15.23), 4));
        tTestSixthGrade.add(getTTestEntity(6, MALE, BigDecimal.valueOf(15.22), BigDecimal.valueOf(13.65), 5));
        tTestSixthGrade.add(getTTestEntity(6, MALE, BigDecimal.valueOf(13.64), BigDecimal.ZERO, 6));
        tTestSixthGrade.add(getTTestEntity(6, FEMALE, BigDecimal.valueOf(100.00), BigDecimal.valueOf(22.51), 2));
        tTestSixthGrade.add(getTTestEntity(6, FEMALE, BigDecimal.valueOf(22.50), BigDecimal.valueOf(20.18), 3));
        tTestSixthGrade.add(getTTestEntity(6, FEMALE, BigDecimal.valueOf(20.17), BigDecimal.valueOf(16.11), 4));
        tTestSixthGrade.add(getTTestEntity(6, FEMALE, BigDecimal.valueOf(16.10), BigDecimal.valueOf(14.36), 5));
        tTestSixthGrade.add(getTTestEntity(6, FEMALE, BigDecimal.valueOf(14.35), BigDecimal.ZERO, 6));
        this.tTestRepository.saveAllAndFlush(tTestSixthGrade);
    }

    private void tTestSeventhGrade() {
        List<TTestEntity> tTestSeventhGrade = new ArrayList<>();
        tTestSeventhGrade.add(getTTestEntity(7, MALE, BigDecimal.valueOf(100.00), BigDecimal.valueOf(20.69), 2));
        tTestSeventhGrade.add(getTTestEntity(7, MALE, BigDecimal.valueOf(20.68), BigDecimal.valueOf(18.60), 3));
        tTestSeventhGrade.add(getTTestEntity(7, MALE, BigDecimal.valueOf(18.59), BigDecimal.valueOf(14.95), 4));
        tTestSeventhGrade.add(getTTestEntity(7, MALE, BigDecimal.valueOf(14.94), BigDecimal.valueOf(13.38), 5));
        tTestSeventhGrade.add(getTTestEntity(7, MALE, BigDecimal.valueOf(13.37), BigDecimal.ZERO, 6));
        tTestSeventhGrade.add(getTTestEntity(7, FEMALE, BigDecimal.valueOf(100.00), BigDecimal.valueOf(22.38), 2));
        tTestSeventhGrade.add(getTTestEntity(7, FEMALE, BigDecimal.valueOf(22.37), BigDecimal.valueOf(20.06), 3));
        tTestSeventhGrade.add(getTTestEntity(7, FEMALE, BigDecimal.valueOf(20.05), BigDecimal.valueOf(15.99), 4));
        tTestSeventhGrade.add(getTTestEntity(7, FEMALE, BigDecimal.valueOf(15.98), BigDecimal.valueOf(14.25), 5));
        tTestSeventhGrade.add(getTTestEntity(7, FEMALE, BigDecimal.valueOf(14.24), BigDecimal.ZERO, 6));
        this.tTestRepository.saveAllAndFlush(tTestSeventhGrade);
    }

    private void tTestEightGrade() {
        List<TTestEntity> tTestEightGrade = new ArrayList<>();
        tTestEightGrade.add(getTTestEntity(8, MALE, BigDecimal.valueOf(100.00), BigDecimal.valueOf(20.33), 2));
        tTestEightGrade.add(getTTestEntity(8, MALE, BigDecimal.valueOf(20.32), BigDecimal.valueOf(18.27), 3));
        tTestEightGrade.add(getTTestEntity(8, MALE, BigDecimal.valueOf(18.26), BigDecimal.valueOf(14.67), 4));
        tTestEightGrade.add(getTTestEntity(8, MALE, BigDecimal.valueOf(14.66), BigDecimal.valueOf(13.12), 5));
        tTestEightGrade.add(getTTestEntity(8, MALE, BigDecimal.valueOf(13.11), BigDecimal.ZERO, 6));
        tTestEightGrade.add(getTTestEntity(8, FEMALE, BigDecimal.valueOf(100.00), BigDecimal.valueOf(22.38), 2));
        tTestEightGrade.add(getTTestEntity(8, FEMALE, BigDecimal.valueOf(22.37), BigDecimal.valueOf(20.06), 3));
        tTestEightGrade.add(getTTestEntity(8, FEMALE, BigDecimal.valueOf(20.05), BigDecimal.valueOf(15.99), 4));
        tTestEightGrade.add(getTTestEntity(8, FEMALE, BigDecimal.valueOf(15.98), BigDecimal.valueOf(14.25), 5));
        tTestEightGrade.add(getTTestEntity(8, FEMALE, BigDecimal.valueOf(14.24), BigDecimal.ZERO, 6));
        this.tTestRepository.saveAllAndFlush(tTestEightGrade);
    }

    private void tTestNinthGrade() {
        List<TTestEntity> tTestNinthGrade = new ArrayList<>();
        tTestNinthGrade.add(getTTestEntity(9, MALE, BigDecimal.valueOf(100.00), BigDecimal.valueOf(20.03), 2));
        tTestNinthGrade.add(getTTestEntity(9, MALE, BigDecimal.valueOf(20.02), BigDecimal.valueOf(17.99), 3));
        tTestNinthGrade.add(getTTestEntity(9, MALE, BigDecimal.valueOf(17.98), BigDecimal.valueOf(14.42), 4));
        tTestNinthGrade.add(getTTestEntity(9, MALE, BigDecimal.valueOf(14.41), BigDecimal.valueOf(12.89), 5));
        tTestNinthGrade.add(getTTestEntity(9, MALE, BigDecimal.valueOf(12.88), BigDecimal.ZERO, 6));
        tTestNinthGrade.add(getTTestEntity(9, FEMALE, BigDecimal.valueOf(100.00), BigDecimal.valueOf(22.27), 2));
        tTestNinthGrade.add(getTTestEntity(9, FEMALE, BigDecimal.valueOf(22.26), BigDecimal.valueOf(19.95), 3));
        tTestNinthGrade.add(getTTestEntity(9, FEMALE, BigDecimal.valueOf(19.94), BigDecimal.valueOf(15.90), 4));
        tTestNinthGrade.add(getTTestEntity(9, FEMALE, BigDecimal.valueOf(15.89), BigDecimal.valueOf(14.16), 5));
        tTestNinthGrade.add(getTTestEntity(9, FEMALE, BigDecimal.valueOf(14.15), BigDecimal.ZERO, 6));
        this.tTestRepository.saveAllAndFlush(tTestNinthGrade);
    }

    private void tTestTenthGrade() {
        List<TTestEntity> tTestNinthGrade = new ArrayList<>();
        tTestNinthGrade.add(getTTestEntity(10, MALE, BigDecimal.valueOf(100.00), BigDecimal.valueOf(19.74), 2));
        tTestNinthGrade.add(getTTestEntity(10, MALE, BigDecimal.valueOf(19.73), BigDecimal.valueOf(17.72), 3));
        tTestNinthGrade.add(getTTestEntity(10, MALE, BigDecimal.valueOf(17.71), BigDecimal.valueOf(14.19), 4));
        tTestNinthGrade.add(getTTestEntity(10, MALE, BigDecimal.valueOf(14.18), BigDecimal.valueOf(12.68), 5));
        tTestNinthGrade.add(getTTestEntity(10, MALE, BigDecimal.valueOf(12.67), BigDecimal.ZERO, 6));
        tTestNinthGrade.add(getTTestEntity(10, FEMALE, BigDecimal.valueOf(100.00), BigDecimal.valueOf(22.27), 2));
        tTestNinthGrade.add(getTTestEntity(10, FEMALE, BigDecimal.valueOf(22.26), BigDecimal.valueOf(19.96), 3));
        tTestNinthGrade.add(getTTestEntity(10, FEMALE, BigDecimal.valueOf(19.95), BigDecimal.valueOf(15.90), 4));
        tTestNinthGrade.add(getTTestEntity(10, FEMALE, BigDecimal.valueOf(15.89), BigDecimal.valueOf(14.17), 5));
        tTestNinthGrade.add(getTTestEntity(10, FEMALE, BigDecimal.valueOf(14.16), BigDecimal.ZERO, 6));
        this.tTestRepository.saveAllAndFlush(tTestNinthGrade);
    }

    private void tTestEleventhGrade() {
        List<TTestEntity> tTestEleventhGrade = new ArrayList<>();
        tTestEleventhGrade.add(getTTestEntity(11, MALE, BigDecimal.valueOf(100.00), BigDecimal.valueOf(19.51), 2));
        tTestEleventhGrade.add(getTTestEntity(11, MALE, BigDecimal.valueOf(19.50), BigDecimal.valueOf(17.51), 3));
        tTestEleventhGrade.add(getTTestEntity(11, MALE, BigDecimal.valueOf(17.50), BigDecimal.valueOf(14.01), 4));
        tTestEleventhGrade.add(getTTestEntity(11, MALE, BigDecimal.valueOf(14.00), BigDecimal.valueOf(12.51), 5));
        tTestEleventhGrade.add(getTTestEntity(11, MALE, BigDecimal.valueOf(12.50), BigDecimal.ZERO, 6));
        tTestEleventhGrade.add(getTTestEntity(11, FEMALE, BigDecimal.valueOf(100.00), BigDecimal.valueOf(22.27), 2));
        tTestEleventhGrade.add(getTTestEntity(11, FEMALE, BigDecimal.valueOf(22.26), BigDecimal.valueOf(19.95), 3));
        tTestEleventhGrade.add(getTTestEntity(11, FEMALE, BigDecimal.valueOf(19.94), BigDecimal.valueOf(15.90), 4));
        tTestEleventhGrade.add(getTTestEntity(11, FEMALE, BigDecimal.valueOf(15.89), BigDecimal.valueOf(14.16), 5));
        tTestEleventhGrade.add(getTTestEntity(11, FEMALE, BigDecimal.valueOf(14.15), BigDecimal.ZERO, 6));
        this.tTestRepository.saveAllAndFlush(tTestEleventhGrade);
    }

    private void tTestTwelfthGrade() {
        List<TTestEntity> tTestTwelfthGrade = new ArrayList<>();
        tTestTwelfthGrade.add(getTTestEntity(12, MALE, BigDecimal.valueOf(100.00), BigDecimal.valueOf(19.51), 2));
        tTestTwelfthGrade.add(getTTestEntity(12, MALE, BigDecimal.valueOf(19.50), BigDecimal.valueOf(17.51), 3));
        tTestTwelfthGrade.add(getTTestEntity(12, MALE, BigDecimal.valueOf(17.50), BigDecimal.valueOf(14.01), 4));
        tTestTwelfthGrade.add(getTTestEntity(12, MALE, BigDecimal.valueOf(14.00), BigDecimal.valueOf(12.51), 5));
        tTestTwelfthGrade.add(getTTestEntity(12, MALE, BigDecimal.valueOf(12.50), BigDecimal.ZERO, 6));
        tTestTwelfthGrade.add(getTTestEntity(12, FEMALE, BigDecimal.valueOf(100.00), BigDecimal.valueOf(22.27), 2));
        tTestTwelfthGrade.add(getTTestEntity(12, FEMALE, BigDecimal.valueOf(22.26), BigDecimal.valueOf(19.95), 3));
        tTestTwelfthGrade.add(getTTestEntity(12, FEMALE, BigDecimal.valueOf(19.94), BigDecimal.valueOf(15.90), 4));
        tTestTwelfthGrade.add(getTTestEntity(12, FEMALE, BigDecimal.valueOf(15.89), BigDecimal.valueOf(14.16), 5));
        tTestTwelfthGrade.add(getTTestEntity(12, FEMALE, BigDecimal.valueOf(14.15), BigDecimal.ZERO, 6));
        this.tTestRepository.saveAllAndFlush(tTestTwelfthGrade);
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
        denseBallTenthGrade.add(getDenseBallEntity(10, MALE, BigDecimal.valueOf(25), BigDecimal.valueOf(9.70), 6));
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
