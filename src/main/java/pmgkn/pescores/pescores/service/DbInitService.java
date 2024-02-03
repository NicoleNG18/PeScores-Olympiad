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

import static pmgkn.pescores.pescores.domain.entity.constants.Messages.FEMALE;
import static pmgkn.pescores.pescores.domain.entity.constants.Messages.MALE;

@Service
public class DbInitService {

    private final UserRoleRepository userRoleRepository;
    private final DenseBallRepository denseBallRepository;

    public DbInitService(UserRoleRepository userRoleRepository,
                         DenseBallRepository denseBallRepository) {
        this.userRoleRepository = userRoleRepository;
        this.denseBallRepository = denseBallRepository;
    }

    @PostConstruct
    public void init() {
        initRoles();
        initDenseBall();
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
