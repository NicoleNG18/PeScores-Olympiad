package pmgkn.pescores.pescores.domain.dto.binding;

import java.math.BigDecimal;
import java.util.UUID;

public class StudentUpdateDto {

    private UUID id;
    private BigDecimal thirtyMeters;
    private BigDecimal twoHundredMeters;
    private BigDecimal tTest;
    private BigDecimal denseBall;
    private BigDecimal jump;

    public StudentUpdateDto() {
    }

    public UUID getId() {
        return id;
    }

    public StudentUpdateDto setId(UUID id) {
        this.id = id;
        return this;
    }

    public BigDecimal getThirtyMeters() {
        return thirtyMeters;
    }

    public StudentUpdateDto setThirtyMeters(BigDecimal thirtyMeters) {
        this.thirtyMeters = thirtyMeters;
        return this;
    }

    public BigDecimal getTwoHundredMeters() {
        return twoHundredMeters;
    }

    public StudentUpdateDto setTwoHundredMeters(BigDecimal twoHundredMeters) {
        this.twoHundredMeters = twoHundredMeters;
        return this;
    }

    public BigDecimal gettTest() {
        return tTest;
    }

    public StudentUpdateDto settTest(BigDecimal tTest) {
        this.tTest = tTest;
        return this;
    }

    public BigDecimal getDenseBall() {
        return denseBall;
    }

    public StudentUpdateDto setDenseBall(BigDecimal denseBall) {
        this.denseBall = denseBall;
        return this;
    }

    public BigDecimal getJump() {
        return jump;
    }

    public StudentUpdateDto setJump(BigDecimal jump) {
        this.jump = jump;
        return this;
    }
}
