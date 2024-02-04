package pmgkn.pescores.pescores.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pmgkn.pescores.pescores.domain.entity.norms.DenseBallEntity;

import java.math.BigDecimal;
import java.util.UUID;

@Repository
public interface DenseBallRepository extends JpaRepository<DenseBallEntity, UUID> {

    @Query("SELECT d FROM DenseBallEntity as d WHERE d.classNum = :n AND d.gender = :g AND :r >= d.min AND :r <= d.max")
    DenseBallEntity getDenseBallEntityByResult(@Param("n") Integer number,
                                               @Param("g") String gender,
                                               @Param("r") BigDecimal result);
}
