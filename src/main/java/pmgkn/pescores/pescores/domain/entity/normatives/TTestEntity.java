package pmgkn.pescores.pescores.domain.entity.normatives;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import pmgkn.pescores.pescores.domain.entity.BaseEntity;

@Entity
@Table(name = "t_test")
public class TTestEntity extends BaseEntity {

    @Column
    private Double result;

    @Column
    private int grade;

    public TTestEntity() {
    }

    public Double getResult() {
        return result;
    }

    public TTestEntity setResult(Double result) {
        this.result = result;
        return this;
    }

    public int getGrade() {
        return grade;
    }

    public TTestEntity setGrade(int grade) {
        this.grade = grade;
        return this;
    }
}



