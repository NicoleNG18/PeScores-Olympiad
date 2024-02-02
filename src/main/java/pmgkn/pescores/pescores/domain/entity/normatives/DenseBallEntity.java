package pmgkn.pescores.pescores.domain.entity.normatives;


import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import pmgkn.pescores.pescores.domain.entity.BaseEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static pmgkn.pescores.pescores.domain.entity.constants.TableNames.DENSE_BALL;

@Entity
@Table(name = DENSE_BALL)
public class DenseBallEntity extends BaseEntity{

    @Column
    private Integer classNum;

    @Column
    private String gender;

    @Column
    private BigDecimal min;

    @Column
    private BigDecimal max;

    @Column
    private int grade;

    public DenseBallEntity() {
    }

    public Integer getClassNum() {
        return classNum;
    }

    public DenseBallEntity setClassNum(Integer classNum) {
        this.classNum = classNum;
        return this;
    }

    public BigDecimal getMin() {
        return min;
    }

    public DenseBallEntity setMin(BigDecimal min) {
        this.min = min;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public DenseBallEntity setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public BigDecimal getMax() {
        return max;
    }

    public DenseBallEntity setMax(BigDecimal max) {
        this.max = max;
        return this;
    }

    public int getGrade() {
        return grade;
    }

    public DenseBallEntity setGrade(int grade) {
        this.grade = grade;
        return this;
    }

}

