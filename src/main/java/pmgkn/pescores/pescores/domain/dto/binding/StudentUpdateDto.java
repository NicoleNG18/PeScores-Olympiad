package pmgkn.pescores.pescores.domain.dto.binding;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import pmgkn.pescores.pescores.domain.enums.GenderEnum;

import java.math.BigDecimal;
import java.util.UUID;

public class StudentUpdateDto {

    private UUID id;

    @Enumerated(EnumType.STRING)
    @NotNull
    private GenderEnum gender;
    private BigDecimal thirtyMeters;
    private BigDecimal twoHundredMeters;
    private BigDecimal tTest;
    private BigDecimal denseBall;
    private BigDecimal jump;

    public StudentUpdateDto() {
    }

    public GenderEnum getGender() {
        return gender;
    }

    public StudentUpdateDto setGender(GenderEnum gender) {
        this.gender = gender;
        return this;
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
