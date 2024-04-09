package pmgkn.pescores.pescores.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pmgkn.pescores.pescores.domain.entity.SchoolEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SchoolRepository extends JpaRepository<SchoolEntity, UUID> {

    Optional<SchoolEntity> findBySchoolName(String name);

    List<SchoolEntity> findAll();

}
