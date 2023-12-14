package pmgkn.pescores.pescores.domain.entity.normatives;


import jakarta.persistence.*;
import pmgkn.pescores.pescores.domain.entity.BaseEntity;

import java.util.List;

@Entity
@Table(name = "dense_ball")
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

