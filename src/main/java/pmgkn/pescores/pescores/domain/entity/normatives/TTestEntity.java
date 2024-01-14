package pmgkn.pescores.pescores.domain.entity.normatives;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import pmgkn.pescores.pescores.domain.entity.BaseEntity;

import static pmgkn.pescores.pescores.domain.entity.constants.TableNames.T_TEST;

@Entity
@Table(name = T_TEST)
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



