package pmgkn.pescores.pescores.domain.entity.normatives;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import pmgkn.pescores.pescores.domain.entity.BaseEntity;

import java.math.BigDecimal;

import static pmgkn.pescores.pescores.domain.entity.constants.TableNames.JUMP;

@Entity
@Table(name = JUMP)
public class JumpEntity extends BaseEntity {

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

    public JumpEntity() {
    }

    public Integer getClassNum() {
        return classNum;
    }

    public JumpEntity setClassNum(Integer classNum) {
        this.classNum = classNum;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public JumpEntity setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public BigDecimal getMin() {
        return min;
    }

    public JumpEntity setMin(BigDecimal min) {
        this.min = min;
        return this;
    }

    public BigDecimal getMax() {
        return max;
    }

    public JumpEntity setMax(BigDecimal max) {
        this.max = max;
        return this;
    }

    public int getGrade() {
        return grade;
    }

    public JumpEntity setGrade(int grade) {
        this.grade = grade;
        return this;
    }
}
