package pmgkn.pescores.pescores.domain.entity.normatives;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import pmgkn.pescores.pescores.domain.entity.BaseEntity;

@Entity
@Table(name = "thirty_meters")
public class ThirtyMetersEntity extends BaseEntity {

    @Column
    private Double result;

    @Column
    private int grade;

    public ThirtyMetersEntity() {
    }

    public Double getResult() {
        return result;
    }

    public ThirtyMetersEntity setResult(Double result) {
        this.result = result;
        return this;
    }

    public int getGrade() {
        return grade;
    }

    public ThirtyMetersEntity setGrade(int grade) {
        this.grade = grade;
        return this;
    }
}


