package pmgkn.pescores.pescores.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pmgkn.pescores.pescores.domain.entity.normatives.DenseBallEntity;
import pmgkn.pescores.pescores.domain.entity.normatives.JumpEntity;

import java.math.BigDecimal;
import java.util.UUID;

@Repository
public interface JumpRepository extends JpaRepository<JumpEntity, UUID> {

    @Query("SELECT j FROM DenseBallEntity as j WHERE j.classNum = :n AND j.gender = :g AND :r >= j.min AND :r <= j.max")
    DenseBallEntity getJumpEntityByResult(@Param("n") Integer number,
                                              @Param("g") String gender,
                                              @Param("r") BigDecimal result);
}
