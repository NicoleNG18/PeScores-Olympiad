package pmgkn.pescores.pescores.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pmgkn.pescores.pescores.domain.entity.norms.TwoHundredMetersEntity;

import java.math.BigDecimal;
import java.util.UUID;

@Repository
public interface TwoHundredMetersRepository extends JpaRepository<TwoHundredMetersEntity, UUID> {

    @Query("SELECT th FROM TwoHundredMetersEntity as th WHERE th.classNum = :n AND th.gender = :g AND :r >= th.min AND :r <= th.max")
    TwoHundredMetersEntity getTwoHundredMetersEntityByResult(@Param("n") Integer number,
                                               @Param("g") String gender,
                                               @Param("r") BigDecimal result);
}
