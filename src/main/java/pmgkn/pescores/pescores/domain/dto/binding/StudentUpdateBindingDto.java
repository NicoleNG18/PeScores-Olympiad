package pmgkn.pescores.pescores.domain.dto.binding;

import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public class StudentUpdateBindingDto {
    @Positive
    private BigDecimal thirtyMeters;
    @Positive
    private BigDecimal twoHundredMeters;
    @Positive
    private BigDecimal tTest;
    @Positive
    private BigDecimal denseBall;
    @Positive
    private BigDecimal jump;

    public StudentUpdateBindingDto() {
    }

    public BigDecimal getThirtyMeters() {
        return thirtyMeters;
    }

    public StudentUpdateBindingDto setThirtyMeters(BigDecimal thirtyMeters) {
        this.thirtyMeters = thirtyMeters;
        return this;
    }

    public BigDecimal getTwoHundredMeters() {
        return twoHundredMeters;
    }

    public StudentUpdateBindingDto setTwoHundredMeters(BigDecimal twoHundredMeters) {
        this.twoHundredMeters = twoHundredMeters;
        return this;
    }

    public BigDecimal gettTest() {
        return tTest;
    }

    public StudentUpdateBindingDto settTest(BigDecimal tTest) {
        this.tTest = tTest;
        return this;
    }

    public BigDecimal getDenseBall() {
        return denseBall;
    }

    public StudentUpdateBindingDto setDenseBall(BigDecimal denseBall) {
        this.denseBall = denseBall;
        return this;
    }

    public BigDecimal getJump() {
        return jump;
    }

    public StudentUpdateBindingDto setJump(BigDecimal jump) {
        this.jump = jump;
        return this;
    }
}
