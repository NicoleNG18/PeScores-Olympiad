package pmgkn.pescores.pescores.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pmgkn.pescores.pescores.domain.entity.norms.ThirtyMetersEntity;

import java.math.BigDecimal;
import java.util.UUID;

@Repository
public interface ThirtyMetersRepository extends JpaRepository<ThirtyMetersEntity, UUID> {

    @Query("SELECT tm FROM ThirtyMetersEntity as tm WHERE tm.classNum = :n AND tm.gender = :g AND :r >= tm.min AND :r <= tm.max")
    ThirtyMetersEntity getThirtyMetersEntityByResult(@Param("n") Integer number,
                                                     @Param("g") String gender,
                                                     @Param("r") BigDecimal result);
}
