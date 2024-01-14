package pmgkn.pescores.pescores.domain.entity.normatives;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import pmgkn.pescores.pescores.domain.entity.BaseEntity;

import static pmgkn.pescores.pescores.domain.entity.constants.TableNames.TWO_HUNDRED_METERS;

@Entity
@Table(name = TWO_HUNDRED_METERS)
public class TwoHundredMetersEntity extends BaseEntity {

    @Column
    private Double result;

    @Column
    private int grade;

    public TwoHundredMetersEntity() {
    }

    public Double getResult() {
        return result;
    }

    public TwoHundredMetersEntity setResult(Double result) {
        this.result = result;
        return this;
    }

    public int getGrade() {
        return grade;
    }

    public TwoHundredMetersEntity setGrade(int grade) {
        this.grade = grade;
        return this;
    }
}



