package pmgkn.pescores.pescores.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pmgkn.pescores.pescores.domain.entity.normatives.TTestEntity;

import java.math.BigDecimal;
import java.util.UUID;

@Repository
public interface TTestRepository extends JpaRepository<TTestEntity, UUID> {

    @Query("SELECT t FROM DenseBallEntity as t WHERE t.classNum = :n AND t.gender = :g AND :r >= t.min AND :r <= t.max")
    TTestEntity getDenseBallEntityByResult(@Param("n") Integer number,
                                               @Param("g") String gender,
                                               @Param("r") BigDecimal result);
}