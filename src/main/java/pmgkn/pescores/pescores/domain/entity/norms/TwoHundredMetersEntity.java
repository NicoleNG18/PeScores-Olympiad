package pmgkn.pescores.pescores.domain.entity.norms;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import pmgkn.pescores.pescores.domain.entity.BaseEntity;

import java.math.BigDecimal;

import static pmgkn.pescores.pescores.domain.entity.constants.TableNames.TWO_HUNDRED_METERS;

@Entity
@Table(name = TWO_HUNDRED_METERS)
public class TwoHundredMetersEntity extends BaseEntity {

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

    public TwoHundredMetersEntity() {
    }

    public Integer getClassNum() {
        return classNum;
    }

    public TwoHundredMetersEntity setClassNum(Integer classNum) {
        this.classNum = classNum;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public TwoHundredMetersEntity setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public BigDecimal getMin() {
        return min;
    }

    public TwoHundredMetersEntity setMin(BigDecimal min) {
        this.min = min;
        return this;
    }

    public BigDecimal getMax() {
        return max;
    }

    public TwoHundredMetersEntity setMax(BigDecimal max) {
        this.max = max;
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



