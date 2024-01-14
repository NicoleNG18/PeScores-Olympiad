package pmgkn.pescores.pescores.domain.entity.normatives;


import jakarta.persistence.*;
import pmgkn.pescores.pescores.domain.entity.BaseEntity;

import java.util.List;

import static pmgkn.pescores.pescores.domain.entity.constants.TableNames.DENSE_BALL;

@Entity
@Table(name = DENSE_BALL)
public class DenseBallEntity extends BaseEntity {

    @Column
    private Double result;

    @Column
    private int grade;

    public DenseBallEntity() {
    }

    public Double getResult() {
        return result;
    }

    public DenseBallEntity setResult(Double result) {
        this.result = result;
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

